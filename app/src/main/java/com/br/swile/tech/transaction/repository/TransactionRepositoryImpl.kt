package com.br.swile.tech.transaction.repository

import com.br.swile.tech.transaction.local.TransactionDao
import com.br.swile.tech.transaction.remote.TransactionDataApi
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class TransactionRepositoryImpl @Inject constructor(
    private val transactionDataApi: TransactionDataApi,
    private val transactionDao: TransactionDao
) : TransactionRepository {

    override fun getTransactions(): Flow<List<String>> = transactionDao.getTransactionEntities()
        .map { entityList -> listOf("Dunga") }

    override suspend fun syncTransactions() {
//        val transactionList = transactionDataApi.getTransactions()
        // TODO saveLocally
    }
}
