package com.br.swile.tech.transaction.ui.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.br.swile.tech.R
import com.br.swile.tech.core.theme.Purple
import com.br.swile.tech.core.util.DateExtension.formatDayMonth
import com.br.swile.tech.model.Currency
import com.br.swile.tech.model.Icon
import com.br.swile.tech.model.Transaction
import com.br.swile.tech.model.TransactionType
import com.br.swile.tech.transaction.ui.TransactionIconImage
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

@Composable
fun TransactionDetailHost(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    viewModel: TransactionsDetailViewModel = hiltViewModel(),
    systemUiController: SystemUiController = rememberSystemUiController()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    TransactionDetailScreen(
        modifier = modifier.fillMaxSize(),
        onBackPressed = onBackPressed,
        uiState = uiState.value,
        systemUiController = systemUiController
    )
}

@Composable
fun TransactionDetailScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    uiState: TransactionDetailUiState,
    systemUiController: SystemUiController
) {
    when (uiState) {
        is TransactionDetailUiState.Error -> {
            SideEffect {
                systemUiController.setStatusBarColor(Color.White)
            }
        }  // TODO implement try again
        is TransactionDetailUiState.Success -> {
            val transaction = uiState.transaction

            SideEffect {
                systemUiController.setStatusBarColor(transaction.largeIcon.type.backgroundColor)
            }

            Column(modifier = modifier) {
                TransactionDetailHeader(
                    modifier = Modifier
                        .heightIn(min = 180.dp, max = 360.dp)
                        .fillMaxWidth(),
                    largeIcon = transaction.largeIcon,
                    smallIcon = transaction.smallIcon,
                    onBackPressed = onBackPressed
                )
                Spacer(modifier = Modifier.size(16.dp))
                TransactionAmountAndDate(
                    modifier = Modifier.fillMaxWidth(),
                    amount = transaction.amount.toString(),
                    description = transaction.description,
                    dateTime = transaction.date,
                )
                Spacer(modifier = Modifier.size(32.dp))
                TransactionDetailActions(
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun TransactionDetailHeader(
    modifier: Modifier = Modifier,
    largeIcon: Icon,
    smallIcon: Icon,
    onBackPressed: () -> Unit
) {
    Box(
        modifier = modifier
            .background(largeIcon.type.backgroundColor)
            .padding(16.dp)
    ) {
        Image(
            modifier = Modifier
                .clickable { onBackPressed() }
                .align(Alignment.TopStart)
                .size(24.dp),
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = stringResource(R.string.close)
        )
        TransactionIconImage(
            icon = largeIcon,
            contentDescription = stringResource(largeIcon.type.description),
            iconSize = 88.dp,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .size(88.dp)
                .align(Alignment.Center)
        )
        TransactionIconImage(
            icon = smallIcon,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(24.dp)
                .align(Alignment.BottomEnd)
        )
    }
}

@Composable
fun TransactionAmountAndDate(
    modifier: Modifier = Modifier,
    amount: String,
    description: String,
    dateTime: Instant
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = amount,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = description,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = dateTime.formatDayMonth(),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.labelMedium,
            color = Color.LightGray
        )
    }
}

@Composable
fun TransactionDetailActions(
    modifier: Modifier = Modifier
) {
    val actions: List<Pair<Int, Int>> = listOf(
        R.drawable.ic_bill_sharing_action to R.string.action_bill_sharing,
        R.drawable.ic_like_action to R.string.action_favorite,
        R.drawable.ic_question_action to R.string.action_problem_report,
    )

    Column(modifier = modifier.padding(12.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RowAction(
                modifier = Modifier.weight(2f),
                icon = R.drawable.ic_meal_action,
                text = stringResource(R.string.action_restaurant_vouchers),
                onActionClick = {}
            )
            Text(
                text = stringResource(id = R.string.action_change_account),
                textAlign = TextAlign.Center,
                color = Purple
            )
        }
        actions.forEach { actionPair ->
            RowAction(
                icon = actionPair.first,
                text = stringResource(id = actionPair.second),
                onActionClick = {}
            )
        }
    }
}

@Composable
fun RowAction(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    text: String,
    iconSize: Dp = 48.dp,
    contentPadding: Dp = 12.dp,
    onActionClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clickable { onActionClick() }
            .padding(contentPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(iconSize),
            painter = painterResource(icon),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(6.dp))
        Text(
            modifier = Modifier
                .weight(2f)
                .padding(end = 16.dp),
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun TransactionDetailScreenPreview() {
    val transaction = Transaction(
        id = "1",
        description = "Mc Donalds",
        type = TransactionType.BURGER,
        date = Clock.System.now(),
        amount = -15.0,
        currency = Currency(
            code = "EUR",
            name = "Euro",
            symbol = "€"
        ),
        smallIcon = Icon(null, TransactionType.BURGER),
        largeIcon = Icon(null, TransactionType.MEAL_VOUCHER),
    )
}