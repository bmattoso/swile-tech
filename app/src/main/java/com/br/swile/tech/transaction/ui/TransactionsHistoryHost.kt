package com.br.swile.tech.transaction.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TransactionsHistoryHost(
    modifier: Modifier = Modifier,
    onTransactionClick: (String) -> Unit,
    viewModel: TransactionsHistoryViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    TransactionsHistoryScreen(
        modifier = modifier,
        uiState = uiState.value,
        onTransactionClick = onTransactionClick
    )
}

@Composable
fun TransactionsHistoryScreen(
    modifier: Modifier = Modifier,
    uiState: TransactionsHistoryUiState,
    onTransactionClick: (String) -> Unit
) {
    Column(modifier = modifier) {
        when (uiState) {
            is TransactionsHistoryUiState.Loading -> {
                Text(text = "Loading Transactions History")
            }
            is TransactionsHistoryUiState.Success -> {
                Text(text = "Transactions History")
            }
        }
    }
}
