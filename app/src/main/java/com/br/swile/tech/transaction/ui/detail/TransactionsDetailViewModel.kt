package com.br.swile.tech.transaction.ui.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.swile.tech.navigation.ARG_TRANSACTION_ID
import com.br.swile.tech.transaction.usecase.GetTransactionDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TransactionsDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getTransactionDetailUseCase: GetTransactionDetailUseCase
) : ViewModel() {

    private val transactionId: String? = savedStateHandle[ARG_TRANSACTION_ID]

    val uiState: StateFlow<TransactionDetailUiState> = getTransactionDetailUseCase(transactionId)
        .also { Log.i("transactionId", transactionId.toString()) }
        .map { transaction ->
            if (transaction == null) {
                TransactionDetailUiState.Error
            } else {
                TransactionDetailUiState.Success(transaction)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = TransactionDetailUiState.Error
        )
}
