package com.br.swile.tech.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.br.swile.tech.R

enum class TransactionType(
    @DrawableRes val defaultIcon: Int,
    @StringRes val description: Int
) {
    MEAL_VOUCHER(R.drawable.ic_launcher_foreground, R.string.transactions),
    DONATION(R.drawable.ic_launcher_foreground, R.string.transactions),
    SUSHI(R.drawable.ic_launcher_foreground, R.string.transactions),
    GIFT(R.drawable.ic_launcher_foreground, R.string.transactions),
    COMPUTER(R.drawable.ic_launcher_foreground, R.string.transactions),
    TRAIN(R.drawable.ic_launcher_foreground, R.string.transactions),
    MOBILITY(R.drawable.ic_launcher_foreground, R.string.transactions),
    BURGER(R.drawable.ic_launcher_foreground, R.string.transactions),
    BAKERY(R.drawable.ic_launcher_foreground, R.string.transactions),
    SUPERMARKET(R.drawable.ic_launcher_foreground, R.string.transactions),
    PAYMENT(R.drawable.ic_launcher_foreground, R.string.transactions),
    UNKNOWN(R.drawable.ic_launcher_foreground, R.string.transactions);
}

fun String.toTransactionType(): TransactionType {
    return TransactionType.values().find { enumTransactionType ->
        enumTransactionType.name.lowercase() == this.lowercase()
    } ?: TransactionType.UNKNOWN
}