package com.br.swile.tech.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.br.swile.tech.transaction.navigation.navigateToTransactionDetail
import com.br.swile.tech.transaction.navigation.transactions

@Composable
fun SwileNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onBackPressed: () -> Unit,
    startNavigation: Destination = Destination.TRANSACTIONS_HISTORY
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startNavigation.route
    ) {
        transactions(
            navigateToTransactionDetail = { transactionId: String ->
                navController.navigateToTransactionDetail(transactionId = transactionId)
            },
            onBackPressed = onBackPressed
        )
    }
}
