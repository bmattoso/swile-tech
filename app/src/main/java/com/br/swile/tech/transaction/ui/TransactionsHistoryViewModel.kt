package com.br.swile.tech.transaction.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.swile.tech.R
import com.br.swile.tech.core.network.NetworkStateProvider
import com.br.swile.tech.core.util.LoadingState
import com.br.swile.tech.core.util.OperationStatus
import com.br.swile.tech.model.Transaction
import com.br.swile.tech.transaction.ui.TransactionsHistoryUiState.Loading
import com.br.swile.tech.transaction.usecase.GetTransactionsUseCase
import com.br.swile.tech.transaction.usecase.SyncTransactionHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsHistoryViewModel @Inject constructor(
    private val networkStateProvider: NetworkStateProvider,
    private val syncTransactionHistoryUseCase: SyncTransactionHistoryUseCase,
    getTransactionsUseCase: GetTransactionsUseCase
) : ViewModel() {

    private val isConnectedState = MutableStateFlow(false)
    private val failedState = MutableStateFlow<Throwable?>(null)
    private val refreshingLoadingState = LoadingState()
    private val transactionsHistoryFlow: Flow<List<Transaction>> = getTransactionsUseCase()
        .catch { throwable ->
            // Send throwable logs to Crash platform
            Log.e("GET-TransactionsHist", throwable.message, throwable)
            failedState.tryEmit(throwable)
        }
        .onEach { refreshingLoadingState.removeLoading() }

    val uiState: StateFlow<TransactionsHistoryUiState> = combine(
        refreshingLoadingState.state,
        transactionsHistoryFlow,
        failedState
    ) { isLoading, transactionList, throwable ->
        when {
            isLoading -> Loading
            throwable != null -> TransactionsHistoryUiState.UnknownError(throwable.toMessage())
            else -> TransactionsHistoryUiState.Success(transactionList)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Loading
    )

    init {
        viewModelScope.launch {
            networkStateProvider.isOnlineFlow.collect { isConnectedState.tryEmit(it) }
        }
        refreshTransactionHistory()
    }

    fun refreshTransactionHistory() {
        viewModelScope.launch {
            if (isConnectedState.value) {
                refreshingLoadingState.addLoading()
                syncTransactionHistoryUseCase().collect { operatorStatus ->
                    if (operatorStatus is OperationStatus.Failed) {
                        failedState.tryEmit(operatorStatus.throwable)
                    }
                }
            }
        }
    }

    private fun Throwable.toMessage(): Int = when (this) {
        is TimeoutCancellationException -> R.string.error_time_out_description
        else -> R.string.error_description_unkown
    }
}
