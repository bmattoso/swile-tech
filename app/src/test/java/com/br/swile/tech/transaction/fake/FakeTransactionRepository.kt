package com.br.swile.tech.transaction.fake

import com.br.swile.tech.model.Transaction
import com.br.swile.tech.transaction.repository.TransactionRepository
import com.google.common.annotations.VisibleForTesting
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class FakeTransactionRepository : TransactionRepository {

    private val _transactionsFlow = MutableStateFlow(listOf<Transaction>())
    private val transactionsFlow: StateFlow<List<Transaction>> = _transactionsFlow.asStateFlow()
    val transactions = mutableListOf<Transaction>()

    override fun getTransactions(): Flow<List<Transaction>> = transactionsFlow

    override fun getTransactionById(transactionId: String?): Flow<Transaction?> {
        return transactionsFlow.map { transactions ->
            transactions.first { it.id == transactionId }
        }
    }

    override suspend fun syncTransactionsRemote() {
        _transactionsFlow.tryEmit(transactions)
    }
}
