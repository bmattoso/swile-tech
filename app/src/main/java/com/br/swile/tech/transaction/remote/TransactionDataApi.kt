package com.br.swile.tech.transaction.remote

import com.br.swile.tech.model.Transaction

interface TransactionDataApi {
    suspend fun getTransactions(): List<Transaction>
}
