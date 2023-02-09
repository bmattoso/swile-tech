package com.br.swile.tech.transaction.fake

import com.br.swile.tech.model.Transaction
import com.br.swile.tech.transaction.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class FakeTransactionRepository : TransactionRepository {

    private val transactionsFlow: MutableStateFlow<List<Transaction>> = MutableStateFlow(listOf())
    val transactions = mutableListOf<Transaction>()

    override fun getTransactions(): Flow<List<Transaction>> = transactionsFlow.asStateFlow()

    override fun getTransactionById(transactionId: String?): Flow<Transaction?> {
        return transactionsFlow.map { transactions ->
            transactions.first { it.id == transactionId }
        }
    }

    override suspend fun syncTransactionsRemote() {
        transactionsFlow.tryEmit(transactions)
    }
}
