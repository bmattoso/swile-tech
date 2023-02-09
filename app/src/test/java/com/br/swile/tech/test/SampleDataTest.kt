package com.br.swile.tech.test

import com.br.swile.tech.model.Currency
import com.br.swile.tech.model.Icon
import com.br.swile.tech.model.Transaction
import com.br.swile.tech.model.TransactionType
import com.br.swile.tech.transaction.local.TransactionEntity
import com.br.swile.tech.transaction.remote.response.AmountResponse
import com.br.swile.tech.transaction.remote.response.CurrencyResponse
import com.br.swile.tech.transaction.remote.response.IconResponse
import com.br.swile.tech.transaction.remote.response.TransactionResponse
import kotlinx.datetime.Clock
import kotlinx.serialization.SerialName

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

    val euroCurrencyResponse = CurrencyResponse(
        isoCode = "EUR",
        title = "Euro",
        symbol = "€"
    )

    val euroCurrency = Currency(
        code = "EUR",
        name = "Euro",
        symbol = "€"
    )

    val mealIconResponse = IconResponse(
        url = "",
        category = "meal_voucher"
    )

    val burgerIconResponse = IconResponse(
        url = "",
        category = "burger"
    )

    val mealTransactionResponse = TransactionResponse(
        name = "",
        type = "meal_voucher",
        date = "2021-03-07T14:04:45.000+01:00",
        message = "",
        amountResponse = AmountResponse(
            value = 15.7,
            currencyResponse = euroCurrencyResponse
        ),
        smallIconResponse = mealIconResponse,
        largeIconResponse = burgerIconResponse
    )

    val mealTransaction = Transaction(
        id = "meal_entity_1",
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

    val mobilityTransactionResponse = TransactionResponse(
        name = "",
        type = "mobility",
        date = "2021-03-07T12:04:45.000+01:00",
        message = "",
        amountResponse = AmountResponse(
            value = 15.7,
            currencyResponse = euroCurrencyResponse
        ),
        smallIconResponse = mealIconResponse,
        largeIconResponse = burgerIconResponse
    )

    val mobilityTransaction = Transaction(
        id = "mobility_entity_1s",
        description = "",
        type = TransactionType.MOBILITY,
        comment = "",
        date = Clock.System.now(),
        amount = 10.0,
        currency = euroCurrency,
        smallIcon = mealIcon,
        largeIcon = burgerIcon
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
