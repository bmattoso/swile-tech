package com.br.swile.tech.transaction.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.br.swile.tech.navigation.ARG_TRANSACTION_ID
import com.br.swile.tech.transaction.usecase.GetTransactionDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionsDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTransactionDetailUseCase: GetTransactionDetailUseCase
) : ViewModel() {

    private val transactionId: String? = savedStateHandle[ARG_TRANSACTION_ID]
}
