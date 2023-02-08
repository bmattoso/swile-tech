package com.br.swile.tech.transaction.ui.detail

import com.br.swile.tech.model.Transaction

sealed class TransactionDetailUiState {
    object Error : TransactionDetailUiState()
    data class Success(val transaction: Transaction) : TransactionDetailUiState()
}
