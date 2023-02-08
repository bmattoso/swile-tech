package com.br.swile.tech.transaction.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.br.swile.tech.R
import com.br.swile.tech.core.component.DefaultLottieAnimation
import com.br.swile.tech.core.component.ImageLoader
import com.br.swile.tech.core.component.ProgressIndicator
import com.br.swile.tech.model.Icon
import com.br.swile.tech.model.Transaction
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TransactionsHistoryHost(
    modifier: Modifier = Modifier,
    onTransactionClick: (String) -> Unit,
    viewModel: TransactionsHistoryViewModel = hiltViewModel(),
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(Color.White)
    }

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 12.dp, start = 12.dp, end = 12.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = stringResource(id = R.string.transactions),
            style = MaterialTheme.typography.titleLarge
        )
        TransactionsHistoryScreen(
            uiState = uiState.value,
            onTransactionClick = onTransactionClick,
            refreshTransactions = { viewModel.refreshTransactionHistory() })
    }
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
            style = MaterialTheme.typography.bodyMedium
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
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun TransactionList(
    modifier: Modifier = Modifier,
    transactions: List<Transaction>,
    onTransactionClick: (String) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(transactions, key = { it.id }) { transaction ->
            TransactionRow(
                modifier = Modifier.padding(top = 6.dp, bottom = 6.dp),
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
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .padding(6.dp)
            .clickable(onClick = { onTransactionClick(transaction.id) }),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Box {
            TransactionIconImage(
                icon = transaction.largeIcon,
                contentDescription = stringResource(transaction.type.description),
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .size(56.dp)
            )
            TransactionIconImage(
                icon = transaction.smallIcon,
                modifier = Modifier
                    .align(BottomEnd)
                    .clip(CircleShape)
                    .size(20.dp)
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(2f),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = transaction.description,
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Normal
            )
            Spacer(modifier = Modifier.size(2.dp))
            Text(
                text = transaction.extraInformation,
                style = MaterialTheme.typography.labelSmall,
                color = Color.LightGray,
                fontStyle = FontStyle.Normal
            )
        }
        Spacer(modifier = Modifier.size(24.dp))
        Box(
            modifier = Modifier
                .widthIn(min = 66.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(transaction.amountBackgroundColor),
            contentAlignment = Center
        ) {
            Text(
                text = transaction.formattedAmount,
                style = MaterialTheme.typography.bodyMedium,
                color = transaction.amountFontColor,
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
fun TransactionIconImage(
    modifier: Modifier = Modifier,
    icon: Icon,
    iconSize: Dp = 24.dp,
    contentDescription: String? = null
) {

    if (icon.url != null) {
        ImageLoader(
            modifier = modifier,
            contentDescription = contentDescription,
            imageUrl = icon.url,
            defaultContentResource = icon.type.defaultIcon
        )
    } else {
        Box(
            modifier = modifier
                .background(icon.type.backgroundColor)
                .border(width = 2.dp, color = icon.type.backgroundColor)
        ) {
            Image(
                modifier = Modifier
                    .size(iconSize)
                    .align(Center),
                painter = painterResource(id = icon.type.defaultIcon),
                contentDescription = contentDescription
            )
        }
    }
}
