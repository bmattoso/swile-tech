package com.br.swile.tech.test

import com.br.swile.tech.model.Currency
import com.br.swile.tech.model.Icon
import com.br.swile.tech.model.Transaction
import com.br.swile.tech.model.TransactionType
import com.br.swile.tech.transaction.local.TransactionEntity
import kotlinx.datetime.Clock

object SampleDataTest {

    val burgerIcon = Icon(
        url = null,
        type = TransactionType.BURGER,
    )

    val mealIcon = Icon(
        url = null,
        type = TransactionType.MEAL_VOUCHER,
    )

    val mealUrlIcon = Icon(
        url = "",
        type = TransactionType.MEAL_VOUCHER,
    )

    val euroCurrency = Currency(
        code = "",
        name = "",
        symbol = ""
    )

    val mealTransaction = Transaction(
        id = "",
        description = "",
        type = TransactionType.MEAL_VOUCHER,
        comment = "",
        date = Clock.System.now(),
        amount = 10.0,
        currency = euroCurrency,
        smallIcon = mealIcon,
        largeIcon = burgerIcon
    )

    val mealTransactionEntity = TransactionEntity(
        id = "meal_entity_1",
        description = "",
        type = TransactionType.MEAL_VOUCHER,
        comment = "",
        date = Clock.System.now(),
        amount = 10.0,
        currencyCode = euroCurrency.code,
        currencyName = euroCurrency.name,
        currencySymbol = euroCurrency.symbol,
        smallIconUrl = "",
        smallIconType = TransactionType.MEAL_VOUCHER,
        largeIconUrl = "",
        largeIconType = TransactionType.BURGER
    )

    val mobilityTransactionEntity = TransactionEntity(
        id = "mobility_entity_1s",
        description = "",
        type = TransactionType.MOBILITY,
        comment = "",
        date = Clock.System.now(),
        amount = 15.0,
        currencyCode = euroCurrency.code,
        currencyName = euroCurrency.name,
        currencySymbol = euroCurrency.symbol,
        smallIconUrl = "",
        smallIconType = TransactionType.MOBILITY,
        largeIconUrl = "",
        largeIconType = TransactionType.TRAIN
    )
}
