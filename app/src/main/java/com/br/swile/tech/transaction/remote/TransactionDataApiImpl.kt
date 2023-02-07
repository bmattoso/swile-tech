package com.br.swile.tech.transaction.remote

import com.br.swile.tech.model.Transaction
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TransactionDataApiImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val transactionService: TransactionService
) : TransactionDataApi {

    override suspend fun getTransactions(): List<Transaction> = withContext(ioDispatcher) {
        val transactionHistoryResponse = transactionService.getTransactions()
        transactionHistoryResponse.toTransactionModelList()
    }
}
