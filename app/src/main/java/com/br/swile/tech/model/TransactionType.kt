package com.br.swile.tech.model

enum class TransactionType {
    MEAL_VOUCHER,
    DONATION,
    SUSHI,
    GIFT,
    COMPUTER,
    TRAIN,
    MOBILITY,
    BURGER,
    BAKERY,
    SUPERMARKET,
    PAYMENT,
    UNKNOWN
}

fun String.toTransactionType(): TransactionType {
    return TransactionType.values().find { enumTransactionType ->
        enumTransactionType.name.lowercase() == this.lowercase()
    } ?: TransactionType.UNKNOWN
}