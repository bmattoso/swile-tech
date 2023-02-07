package com.br.swile.tech.transaction.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AmountResponse(
    val value: Double,
    @SerialName("currency") val currencyResponse: CurrencyResponse
)
