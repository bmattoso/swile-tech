package com.br.swile.tech.transaction.remote.response

import com.br.swile.tech.core.util.DateExtension.toDate
import com.br.swile.tech.model.Transaction
import com.br.swile.tech.model.toTransactionType
import kotlinx.serialization.Serializable

@Serializable
data class TransactionHistoryResponse(
    val transactions: List<TransactionResponse>
) {

    fun toTransactionModelList(): List<Transaction> = transactions.map { transactionResponse ->
        with(transactionResponse) {
            Transaction(
                id = "$name-$type-$message", // Assuming receiving id from API
                description = name,
                type = type.toTransactionType(),
                comment = message,
                date = date.toDate(),
                amount = amountResponse.value,
                currency = amountResponse.currencyResponse.toModel(),
                smallIcon = smallIconResponse.toModel(),
                largeIcon = largeIconResponse.toModel()
            )
        }
    }
}
