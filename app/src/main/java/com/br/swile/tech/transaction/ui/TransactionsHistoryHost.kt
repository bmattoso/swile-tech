package com.br.swile.tech.transaction.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.br.swile.tech.R
import com.br.swile.tech.core.component.DefaultLottieAnimation
import com.br.swile.tech.core.component.ProgressIndicator
import com.br.swile.tech.model.Transaction

@Composable
fun TransactionsHistoryHost(
    modifier: Modifier = Modifier,
    onTransactionClick: (String) -> Unit,
    viewModel: TransactionsHistoryViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    TransactionsHistoryScreen(
        modifier = modifier.fillMaxSize(),
        uiState = TransactionsHistoryUiState.Success(emptyList()),
        onTransactionClick = onTransactionClick,
        refreshTransactions = { viewModel.refreshTransactionHistory() })
}

@Composable
fun TransactionsHistoryScreen(
    modifier: Modifier = Modifier,
    uiState: TransactionsHistoryUiState,
    onTransactionClick: (String) -> Unit,
    refreshTransactions: () -> Unit
) {
    Column(modifier = modifier) {
        when (uiState) {
            is TransactionsHistoryUiState.Loading -> {
                ProgressIndicator(modifier = Modifier.fillMaxSize())
            }

            is TransactionsHistoryUiState.UnknownError -> {
                TransactionsHistoryProblem(
                    modifier = Modifier.fillMaxSize(),
                    message = stringResource(id = uiState.message),
                    onTryAgain = refreshTransactions
                )
            }

            is TransactionsHistoryUiState.Success -> {
                // TODO implement swipe refresh
                if (uiState.transactions.isEmpty()) {
                    EmptyTransactionHistory(modifier = Modifier.fillMaxSize())
                } else {
                    TransactionList(
                        modifier = Modifier.fillMaxSize(),
                        transactions = uiState.transactions,
                        onTransactionClick = onTransactionClick
                    )
                }
            }
        }
    }
}

@Composable
fun TransactionsHistoryProblem(
    modifier: Modifier = Modifier,
    message: String,
    onTryAgain: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DefaultLottieAnimation(
            modifier = Modifier.size(200.dp),
            animationResId = R.raw.common_error,
            iterations = 2
        )
        Text(
            text = message,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = onTryAgain) {
            Text(text = stringResource(id = R.string.try_again))
        }
    }
}

@Composable
fun EmptyTransactionHistory(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = CenterHorizontally,
    ) {
        DefaultLottieAnimation(
            modifier = Modifier.size(360.dp),
            animationResId = R.raw.empty_state,
            iterations = 6
        )
        Text(
            text = stringResource(id = R.string.empty_transaction_history),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun TransactionList(
    modifier: Modifier = Modifier,
    transactions: List<Transaction>,
    onTransactionClick: (String) -> Unit
) {
    LazyColumn() {
        items(transactions, key = { it.id }) { transaction ->
            TransactionRow(
                transaction = transaction,
                onTransactionClick = onTransactionClick
            )
        }
    }
}

@Composable
fun TransactionRow(
    modifier: Modifier = Modifier,
    transaction: Transaction,
    onTransactionClick: (String) -> Unit
) {
    Row(
        modifier = modifier
            .heightIn(min = 48.dp)
            .clickable(onClick = { onTransactionClick(transaction.id) })
    ) {
        Box {
            //Image()
            // Image(modifier = Modifier.bottom.right)
        }
        Column {
            Text(text = transaction.description)
            Text(text = transaction.extraInformation)
        }
        Text(text = transaction.amount.toString())
    }
}
