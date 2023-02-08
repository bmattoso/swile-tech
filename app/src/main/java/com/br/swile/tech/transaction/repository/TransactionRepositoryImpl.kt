package com.br.swile.tech.transaction.repository

import com.br.swile.tech.model.Currency
import com.br.swile.tech.model.Icon
import com.br.swile.tech.model.Transaction
import com.br.swile.tech.transaction.local.TransactionDao
import com.br.swile.tech.transaction.local.TransactionEntity
import com.br.swile.tech.transaction.remote.TransactionDataApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val transactionDataApi: TransactionDataApi,
    private val transactionDao: TransactionDao
) : TransactionRepository {

    override fun getTransactions(): Flow<List<Transaction>> =
        transactionDao.getTransactionEntities().map { entityList ->
            entityList.map { entity -> entity.mapTransactionEntityToModel() }
        }

    override fun getTransactionById(transactionId: String?): Flow<Transaction?> =
        transactionDao.getTransactionById(transactionId).map { transactionEntity ->
            transactionEntity?.mapTransactionEntityToModel()
        }

    override suspend fun syncTransactionsRemote() {
        val transactionList = transactionDataApi.getTransactions()
        val transactionEntities = transactionList.map { it.mapTransactionToEntity() }
        transactionDao.insertTransactionList(transactionEntities)
    }

    private fun Transaction.mapTransactionToEntity() = TransactionEntity(
        id = id,
        description = description,
        date = date,
        type = type,
        amount = amount,
        comment = comment,
        currencyCode = currency.code,
        currencyName = currency.name,
        currencySymbol = currency.symbol,
        smallIconUrl = smallIcon.url,
        smallIconType = smallIcon.type,
        largeIconUrl = largeIcon.url,
        largeIconType = largeIcon.type,
    )

    private fun TransactionEntity.mapTransactionEntityToModel() = Transaction(
        id = id,
        description = description,
        date = date,
        type = type,
        comment = comment,
        amount = amount,
        currency = Currency(
            code = currencyCode,
            name = currencyName,
            symbol = currencySymbol
        ),
        smallIcon = Icon(smallIconUrl, smallIconType),
        largeIcon = Icon(largeIconUrl, largeIconType)
    )
}
