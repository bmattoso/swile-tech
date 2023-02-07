package com.br.swile.tech.transaction.repository

import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun getTransactions(): Flow<List<String>>
    suspend fun syncTransactions()
}