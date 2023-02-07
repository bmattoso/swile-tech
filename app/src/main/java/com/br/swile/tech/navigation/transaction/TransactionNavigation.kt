package com.br.swile.tech.navigation.transaction

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.br.swile.tech.transaction.ui.TransactionsHistoryHost

private const val TRANSACTION_ID_ARG = "transactionId"

const val transactionsRoute = "transactions"
const val transactionDetailsRoute = "transactions/{$TRANSACTION_ID_ARG}"

fun NavController.navigateToTransactionHistory(navOptions: NavOptions? = null) {
    navigate(transactionsRoute, navOptions)
}

fun NavController.navigateToTransactionDetail(
    navOptions: NavOptions? = null,
    transactionId: String
) {
    navigate(
        transactionDetailsRoute.replace(TRANSACTION_ID_ARG, transactionId),
        navOptions
    )
}

fun NavGraphBuilder.transactions(
    navigateToTransactionDetail: (String) -> Unit,
    onBackPressed: () -> Unit
) {
    composable(route = transactionsRoute) {
        TransactionsHistoryHost(
            onTransactionClick = navigateToTransactionDetail
        )
    }

    composable(
        route = transactionDetailsRoute,
        arguments = listOf(navArgument(TRANSACTION_ID_ARG) { type = NavType.IntType })
    ) {
    }
}
