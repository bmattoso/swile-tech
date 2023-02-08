package com.br.swile.tech.transaction.ui

import com.br.swile.tech.model.Transaction

sealed interface TransactionsHistoryUiState {
    object Loading : TransactionsHistoryUiState
    data class Success(val transactions: List<Transaction>) : TransactionsHistoryUiState
    data class UnknownError(val message: Int) : TransactionsHistoryUiState
}