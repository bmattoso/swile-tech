package com.br.swile.tech.transaction.remote.response

import com.br.swile.tech.model.Icon
import com.br.swile.tech.model.toTransactionType
import kotlinx.serialization.Serializable

@Serializable
data class IconResponse(
    val url: String?,
    val category: String
) {
    fun toModel() = Icon(
        url = url,
        type = category.toTransactionType()
    )
}
