package com.br.swile.tech.transaction.repository

import com.br.swile.tech.transaction.remote.TransactionDataApi
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TransactionRepositoryImpl @Inject constructor(
    private val transactionDataApi: TransactionDataApi,
) : TransactionRepository {

    override fun getTransactions(): Flow<List<String>> = flowOf(emptyList()) // TODO return locally

    override suspend fun syncTransactions() {
//        val transactionList = transactionDataApi.getTransactions()
        // TODO saveLocally
    }
}
