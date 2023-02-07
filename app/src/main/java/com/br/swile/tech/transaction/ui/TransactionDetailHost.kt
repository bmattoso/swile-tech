package com.br.swile.tech.transaction.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TransactionDetailHost(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    transactionId: String
) {
    TransactionDetailScreen(
        modifier = modifier,
        onBackPressed = onBackPressed,
        transactionId = transactionId
    )
}

@Composable
fun TransactionDetailScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    transactionId: String
) {
    Column(modifier = modifier) {
        Text(text = "Transaction Detail - $transactionId")
    }
}
