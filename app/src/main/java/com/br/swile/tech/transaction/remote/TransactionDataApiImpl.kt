package com.br.swile.tech.transaction.remote

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TransactionDataApiImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val transactionService: TransactionService
) : TransactionDataApi {

    override suspend fun getTransactions(): List<String> = withContext(ioDispatcher) {
        transactionService.getTransactions()
    }
}
