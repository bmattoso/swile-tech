package com.br.swile.tech.transaction.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.swile.tech.core.NetworkStateProvider
import com.br.swile.tech.transaction.ui.TransactionsHistoryUiState.Loading
import com.br.swile.tech.transaction.usecase.GetTransactionsUseCase
import com.br.swile.tech.transaction.usecase.SyncTransactionHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsHistoryViewModel @Inject constructor(
    networkStateProvider: NetworkStateProvider,
    getTransactionsUseCase: GetTransactionsUseCase,
    syncTransactionHistoryUseCase: SyncTransactionHistoryUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            val isOnline = networkStateProvider.isOnlineFlow.first { isOnline -> isOnline }
            if (isOnline) {
                syncTransactionHistoryUseCase()
            }
        }
    }

    val uiState: StateFlow<TransactionsHistoryUiState> = getTransactionsUseCase()
        .filter { it.isNotEmpty() }
        .map { transactions ->
            TransactionsHistoryUiState.Success(transactions.map { it.description })
        }.catch { throwable ->
            // Send throwable logs to Crash platform
            TransactionsHistoryUiState.UnknownError(throwable)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = Loading
        )
}
