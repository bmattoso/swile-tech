package com.br.swile.tech.transaction.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TransactionsHistoryHost(
    modifier: Modifier = Modifier,
    onTransactionClick: (String) -> Unit
) {
    TransactionsHistoryScreen(
        modifier = modifier,
        onTransactionClick = onTransactionClick
    )
}

@Composable
fun TransactionsHistoryScreen(
    modifier: Modifier = Modifier,
    onTransactionClick: (String) -> Unit
) {
    Column(modifier = modifier) {
        Text(text = "Transactions History")
    }
}
