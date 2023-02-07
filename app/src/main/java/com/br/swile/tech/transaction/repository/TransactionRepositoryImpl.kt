package com.br.swile.tech.transaction.repository

import com.br.swile.tech.model.Currency
import com.br.swile.tech.model.Icon
import com.br.swile.tech.model.Transaction
import com.br.swile.tech.transaction.local.TransactionDao
import com.br.swile.tech.transaction.local.TransactionEntity
import com.br.swile.tech.transaction.remote.TransactionDataApi
import java.util.Date
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TransactionRepositoryImpl @Inject constructor(
    private val transactionDataApi: TransactionDataApi,
    private val transactionDao: TransactionDao
) : TransactionRepository {

    override fun getTransactions(): Flow<List<Transaction>> =
        transactionDao.getTransactionEntities()
            .map { entityList ->
                entityList.map { entity -> mapTransactionEntityToModel(entity) }
            }

    override suspend fun syncTransactionsRemote() {
        val transactionList = transactionDataApi.getTransactions()
        val transactionEntities = transactionList.map { mapTransactionToEntity(it) }
        transactionDao.insertTransactionList(transactionEntities)
    }

    private fun mapTransactionToEntity(transaction: Transaction) = TransactionEntity(
        id = transaction.id,
        description = transaction.description,
        date = transaction.date,
        type = transaction.type,
        amount = transaction.amount,
        currencyCode = transaction.currency.code,
        currencyName = transaction.currency.name,
        currencySymbol = transaction.currency.symbol,
        smallIconUrl = transaction.smallIcon.url,
        smallIconType = transaction.smallIcon.type,
        largeIconUrl = transaction.largeIcon.url,
        largeIconType = transaction.largeIcon.type,
    )

    private fun mapTransactionEntityToModel(entity: TransactionEntity) = Transaction(
        id = entity.id,
        description = entity.description,
        date = entity.date,
        type = entity.type,
        amount = entity.amount,
        currency = Currency(
            code = entity.currencyCode,
            name = entity.currencyName,
            symbol = entity.currencySymbol
        ),
        smallIcon = Icon(entity.smallIconUrl, entity.smallIconType),
        largeIcon = Icon(entity.largeIconUrl, entity.largeIconType)
    )
}
