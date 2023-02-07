package com.br.swile.tech.navigation

import com.br.swile.tech.navigation.transaction.transactionDetailsRoute
import com.br.swile.tech.navigation.transaction.transactionsRoute

enum class Destination(
    val route: String
) {
    TRANSACTIONS_HISTORY(transactionsRoute),
    TRANSACTION_DETAIL(transactionDetailsRoute);
}
