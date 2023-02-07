package com.br.swile.tech.transaction.remote.response

import com.br.swile.tech.model.Currency
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyResponse(
    @SerialName("iso_3") val isoCode: String,
    val symbol: String,
    val title: String
) {
    fun toModel() = Currency(
        code = isoCode,
        name = title,
        symbol = symbol,
    )
}
