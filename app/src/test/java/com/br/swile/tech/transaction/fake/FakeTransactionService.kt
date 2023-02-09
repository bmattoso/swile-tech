package com.br.swile.tech.transaction.fake

import com.br.swile.tech.test.SampleDataTest.mealTransactionResponse
import com.br.swile.tech.test.SampleDataTest.mobilityTransactionResponse
import com.br.swile.tech.transaction.remote.TransactionService
import com.br.swile.tech.transaction.remote.response.TransactionHistoryResponse

class FakeTransactionService : TransactionService {

    var transactionHistoryResponse = TransactionHistoryResponse(
        listOf(mealTransactionResponse, mobilityTransactionResponse)
    )

    override suspend fun getTransactions(): TransactionHistoryResponse = transactionHistoryResponse
}
