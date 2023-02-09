package com.br.swile.tech.transaction.fake

import com.br.swile.tech.model.Transaction
import com.br.swile.tech.transaction.remote.TransactionDataApi

class FakeTransactionDataApi : TransactionDataApi {

    val remoteTransactionList: MutableList<Transaction> = mutableListOf()

    override suspend fun getTransactions() = remoteTransactionList
}
