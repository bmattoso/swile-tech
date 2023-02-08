package com.br.swile.tech.transaction.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.br.swile.tech.navigation.ARG_TRANSACTION_ID
import com.br.swile.tech.navigation.Destination
import com.br.swile.tech.transaction.ui.TransactionsHistoryHost
import com.br.swile.tech.transaction.ui.detail.TransactionDetailHost

fun NavController.navigateToTransactionDetail(
    navOptions: NavOptions? = null,
    transactionId: String
) {
    navigate(
        navOptions = navOptions,
        route = Destination.TRANSACTION_DETAIL.route
            .replace("{","")
            .replace("}","")
            .replace(ARG_TRANSACTION_ID, transactionId)
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
        TransactionDetailHost(onBackPressed = onBackPressed)
    }
}
