package com.br.swile.tech.transaction.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionResponse(
    val name: String,
    val type: String,
    val date: String,
    val message: String?,
    @SerialName("amount") val amountResponse: AmountResponse,
    @SerialName("small_icon") val smallIconResponse: IconResponse,
    @SerialName("large_icon") val largeIconResponse: IconResponse
)
