package com.br.swile.tech.transaction.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.swile.tech.transaction.ui.TransactionsHistoryUiState.Loading
import com.br.swile.tech.transaction.usecase.GetTransactionsUseCase
import com.br.swile.tech.transaction.usecase.SyncTransactionHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class TransactionsHistoryViewModel @Inject constructor(
    getTransactionsUseCase: GetTransactionsUseCase,
    syncTransactionHistoryUseCase: SyncTransactionHistoryUseCase
) : ViewModel() {

    init {
        viewModelScope.launch { syncTransactionHistoryUseCase() }
    }

    val uiState: StateFlow<TransactionsHistoryUiState> = getTransactionsUseCase()
        .filter { it.isNotEmpty() }
        .map { transactions ->
            TransactionsHistoryUiState.Success(transactions.map { it.description })
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = Loading
        )
}
