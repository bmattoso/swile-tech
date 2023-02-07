package com.br.swile.tech.transaction.ui

import androidx.lifecycle.ViewModel
import com.br.swile.tech.transaction.ui.TransactionsHistoryUiState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class TransactionsHistoryViewModel @Inject constructor() : ViewModel() {

    val uiState: MutableStateFlow<TransactionsHistoryUiState> = MutableStateFlow(Loading)

}
