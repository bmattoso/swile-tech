package com.br.swile.tech.transaction.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.br.swile.tech.navigation.Destination
import com.br.swile.tech.navigation.ARG_TRANSACTION_ID
import com.br.swile.tech.transaction.ui.TransactionDetailHost
import com.br.swile.tech.transaction.ui.TransactionsHistoryHost

fun NavController.navigateToTransactionDetail(
    navOptions: NavOptions? = null,
    transactionId: String
) {
    navigate(
        Destination.TRANSACTION_DETAIL.route.replace(ARG_TRANSACTION_ID, transactionId),
        navOptions
    )
}

fun NavGraphBuilder.transactions(
    navigateToTransactionDetail: (String) -> Unit,
    onBackPressed: () -> Unit
) {
    composable(route = Destination.TRANSACTIONS_HISTORY.route) {
        TransactionsHistoryHost(
            onTransactionClick = navigateToTransactionDetail
        )
    }

    composable(
        route = Destination.TRANSACTION_DETAIL.route,
        arguments = listOf(navArgument(ARG_TRANSACTION_ID) { type = NavType.StringType })
    ) {
        val transactionId = it.arguments?.getString(ARG_TRANSACTION_ID) ?: ""
        TransactionDetailHost(onBackPressed = onBackPressed, transactionId = transactionId)
    }
}
