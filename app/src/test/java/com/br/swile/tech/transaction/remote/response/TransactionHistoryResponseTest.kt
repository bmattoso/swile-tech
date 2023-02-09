package com.br.swile.tech.transaction.remote.response

import com.br.swile.tech.model.TransactionType
import com.br.swile.tech.test.SampleDataTest.mealTransactionResponse
import kotlinx.datetime.Instant
import org.junit.Assert.assertEquals
import org.junit.Test

class TransactionHistoryResponseTest {

    @Test
    fun validateResponseParse_toTransactionModelList() {
        val transactionHistoryResponse = TransactionHistoryResponse(
            transactions = listOf(mealTransactionResponse)
        )

        val transactionModelList = transactionHistoryResponse.toTransactionModelList()

        val transaction = transactionModelList[0]
        val expectedId =
            "${mealTransactionResponse.name}-${mealTransactionResponse.type}-${mealTransactionResponse.message}"

        assertEquals(expectedId, transaction.id)
        assertEquals(mealTransactionResponse.name, transaction.description)
        assertEquals(mealTransactionResponse.message, transaction.comment)
        assertEquals(mealTransactionResponse.amountResponse.value, transaction.amount, 0.0)
        assertEquals(TransactionType.MEAL_VOUCHER, transaction.type)
        assertEquals(mealTransactionResponse.smallIconResponse.url, transaction.smallIcon.url)
        assertEquals(TransactionType.MEAL_VOUCHER, transaction.smallIcon.type)
        assertEquals(mealTransactionResponse.largeIconResponse.url, transaction.largeIcon.url)
        assertEquals(TransactionType.BURGER, transaction.largeIcon.type)
        assertEquals(Instant.parse(mealTransactionResponse.date), transaction.date)
    }
}
