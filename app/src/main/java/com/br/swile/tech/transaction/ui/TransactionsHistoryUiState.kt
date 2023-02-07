package com.br.swile.tech.transaction.ui

sealed interface TransactionsHistoryUiState {
    object Loading : TransactionsHistoryUiState
    data class Success(val transactions: List<String>) : TransactionsHistoryUiState
}