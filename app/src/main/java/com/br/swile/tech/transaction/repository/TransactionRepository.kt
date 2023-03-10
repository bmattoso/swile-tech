package com.br.swile.tech.transaction.repository

import com.br.swile.tech.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun getTransactions(): Flow<List<Transaction>>
    fun getTransactionById(transactionId: String?): Flow<Transaction?>
    suspend fun syncTransactionsRemote()
}
