package com.br.swile.tech.navigation

const val ARG_TRANSACTION_ID = "transactionId"
private const val transactionsRoute = "transactions"
private const val transactionDetailsRoute = "transactions/{$ARG_TRANSACTION_ID}"

enum class Destination(val route: String) {
    TRANSACTIONS_HISTORY(transactionsRoute),
    TRANSACTION_DETAIL(transactionDetailsRoute);
}
