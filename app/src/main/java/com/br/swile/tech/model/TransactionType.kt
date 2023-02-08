package com.br.swile.tech.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.br.swile.tech.R
import com.br.swile.tech.core.theme.LightBlueMagenta
import com.br.swile.tech.core.theme.LightCyan
import com.br.swile.tech.core.theme.LightOrange
import com.br.swile.tech.core.theme.LightPink
import com.br.swile.tech.core.theme.LightRed

enum class TransactionType(
    @DrawableRes val defaultIcon: Int,
    @StringRes val description: Int,
    val backgroundColor: Color
) {
    MEAL_VOUCHER(R.drawable.ic_meal_voucher, R.string.transactions, Color.White),
    DONATION(R.drawable.ic_donation, R.string.transactions, LightCyan),
    SUSHI(R.drawable.ic_sushi, R.string.transactions, LightOrange),
    GIFT(R.drawable.ic_gift, R.string.transactions, Color.White),
    COMPUTER(R.drawable.ic_tech, R.string.transactions, LightPink),
    TRAIN(R.drawable.ic_subway, R.string.transactions, LightRed),
    MOBILITY(R.drawable.ic_bike, R.string.transactions, Color.White),
    BURGER(R.drawable.ic_hamburger, R.string.transactions, LightOrange),
    BAKERY(R.drawable.ic_bakery, R.string.transactions, LightOrange),
    SUPERMARKET(R.drawable.ic_supermarket, R.string.transactions, LightOrange),
    PAYMENT(R.drawable.ic_payment, R.string.transactions, LightBlueMagenta),
    UNKNOWN(R.drawable.ic_unknown, R.string.transactions, Color.Gray);
}

fun String.toTransactionType(): TransactionType {
    return TransactionType.values().find { enumTransactionType ->
        enumTransactionType.name.lowercase() == this.lowercase()
    } ?: TransactionType.UNKNOWN
}