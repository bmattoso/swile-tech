package com.br.swile.tech.transaction.repository

import com.br.swile.tech.core.database.DatabaseModule
import com.br.swile.tech.test.SampleDataTest.mealTransaction
import com.br.swile.tech.test.SampleDataTest.mealTransactionEntity
import com.br.swile.tech.test.SampleDataTest.mobilityTransaction
import com.br.swile.tech.test.SampleDataTest.mobilityTransactionEntity
import com.br.swile.tech.test.SwileDatabaseApplicationTest
import com.br.swile.tech.transaction.local.TransactionDao
import com.br.swile.tech.transaction.remote.TransactionDataApi
import com.br.swile.tech.transaction.fake.FakeTransactionDataApi
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

@UninstallModules(DatabaseModule::class)
@HiltAndroidTest
class TransactionRepositoryTest : SwileDatabaseApplicationTest() {

    private val transactionDataApi: TransactionDataApi = FakeTransactionDataApi()

    @Inject
    lateinit var transactionDao: TransactionDao

    lateinit var repository: TransactionRepository

    @Before
    fun setup() {
        hiltRule.inject()

        repository = TransactionRepositoryImpl(
            transactionDao = transactionDao,
            transactionDataApi = transactionDataApi
        )
    }

    @Test
    fun getTransactions_returnTransactionsSavedIntoDatabase() = runTest {
        val expectedTransactions = listOf(mealTransactionEntity, mobilityTransactionEntity)
        transactionDao.insertTransactionList(transactionList = expectedTransactions)

        val resultTransactions = repository.getTransactions().first()

        assertEquals(expectedTransactions.size, resultTransactions.size)
        expectedTransactions.forEachIndexed { index, entity ->
            // TODO validate all fields
            assertEquals(entity.id, resultTransactions[index].id)
            assertEquals(entity.description, resultTransactions[index].description)
        }
    }

    @Test
    fun getTransactionById_returnFlowWithSavedTransaction() = runTest {
        val expectedTransactions = listOf(mealTransactionEntity, mobilityTransactionEntity)
        transactionDao.insertTransactionList(transactionList = expectedTransactions)

        val resultTransaction = repository.getTransactionById(mobilityTransactionEntity.id).first()

        assertNotNull(resultTransaction)
        assertEquals(mobilityTransactionEntity.id, resultTransaction?.id)
        assertEquals(mobilityTransactionEntity.description, resultTransaction?.description)
    }

    @Test
    fun syncTransactionsRemote() = runTest {
        val remoteTransactionList = listOf(mealTransaction, mobilityTransaction)
        (transactionDataApi as FakeTransactionDataApi).remoteTransactionList.apply {
            clear()
            addAll(remoteTransactionList)
        }

        repository.syncTransactionsRemote()

        val savedEntities = transactionDao.getTransactionEntities().first()
        assertEquals(remoteTransactionList.size, savedEntities.size)
        remoteTransactionList.forEachIndexed { index, transaction ->
            assertEquals(transaction.id, savedEntities[index].id)
        }
    }
}
