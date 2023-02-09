package com.br.swile.tech.transaction.local

import com.br.swile.tech.core.database.DatabaseModule
import com.br.swile.tech.core.database.SwileDatabase
import com.br.swile.tech.test.SampleDataTest.mealTransactionEntity
import com.br.swile.tech.test.SampleDataTest.mobilityTransactionEntity
import com.br.swile.tech.test.SwileDatabaseApplicationTest
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

@UninstallModules(DatabaseModule::class)
@HiltAndroidTest
class TransactionDaoTest : SwileDatabaseApplicationTest() {

    @Inject
    lateinit var database: SwileDatabase

    @Inject
    lateinit var transactionDao: TransactionDao

    @Before
    fun setup() {
        hiltRule.inject()

        // Working with 1 transaction already saved
        runBlocking {
            transactionDao.insertTransactionList(listOf(mealTransactionEntity))
        }
    }

    @Test
    fun getTransactionEntities_returnAllSavedTransactions() = runTest {
        val transactions = transactionDao.getTransactionEntities().first()
        assertEquals(1, transactions.size)

        // TODO Add Truth library to help validate same class type fields
        assertEquals(mealTransactionEntity.id, transactions[0].id)
    }

    @Test
    fun insertTransactionList_shouldSaveTransaction() = runTest {
        val newTransactionList: List<TransactionEntity> = listOf(mobilityTransactionEntity)

        transactionDao.insertTransactionList(newTransactionList)

        val transactions = transactionDao.getTransactionEntities().first()
        assertEquals(2, transactions.size)
        assertEquals(mobilityTransactionEntity.id, transactions[1].id)
    }

    @Test
    fun getTransactionById_returnSavedTransactionFound() = runTest {
        val transaction = transactionDao.getTransactionById(mealTransactionEntity.id).first()

        assertNotNull(transaction)
        assertEquals(mobilityTransactionEntity.id, transaction?.id)
    }
}
