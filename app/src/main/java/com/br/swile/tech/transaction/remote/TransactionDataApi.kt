package com.br.swile.tech.transaction.remote

interface TransactionDataApi {
    suspend fun getTransactions(): List<String>
}
