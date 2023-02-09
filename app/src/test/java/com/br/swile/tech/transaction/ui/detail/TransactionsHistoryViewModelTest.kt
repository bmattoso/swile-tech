package com.br.swile.tech.transaction.ui.detail

import com.br.swile.tech.fake.FakeNetworkStateProvider
import com.br.swile.tech.test.MainDispatcherRule
import com.br.swile.tech.test.SampleDataTest.mealTransaction
import com.br.swile.tech.transaction.fake.FakeTransactionRepository
import com.br.swile.tech.transaction.ui.TransactionsHistoryUiState
import com.br.swile.tech.transaction.ui.TransactionsHistoryViewModel
import com.br.swile.tech.transaction.usecase.GetTransactionsUseCase
import com.br.swile.tech.transaction.usecase.SyncTransactionHistoryUseCase
import io.mockk.coVerify
import io.mockk.spyk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class TransactionsHistoryViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val networkStateProvider = FakeNetworkStateProvider()
    private val transactionRepository = FakeTransactionRepository()
    private val syncTransactionHistoryUseCase =
        spyk(SyncTransactionHistoryUseCase(transactionRepository))
    private val getTransactionsUseCase = spyk(GetTransactionsUseCase(transactionRepository))
    private lateinit var viewModel: TransactionsHistoryViewModel

    @Test
    fun syncTransactions_whenIsConnected() = runTest {
        networkStateProvider.setIsConnected(true)

        viewModel = TransactionsHistoryViewModel(
            networkStateProvider = networkStateProvider,
            syncTransactionHistoryUseCase = syncTransactionHistoryUseCase,
            getTransactionsUseCase = getTransactionsUseCase
        )

        coVerify(exactly = 1) { syncTransactionHistoryUseCase() }
        coVerify(exactly = 1) { getTransactionsUseCase() }
    }

    @Test
    fun doNotSyncTransactions_whenIsDisconnected() = runTest {
        networkStateProvider.setIsConnected(false)

        viewModel = TransactionsHistoryViewModel(
            networkStateProvider = networkStateProvider,
            syncTransactionHistoryUseCase = syncTransactionHistoryUseCase,
            getTransactionsUseCase = getTransactionsUseCase
        )

        coVerify(exactly = 0) { syncTransactionHistoryUseCase() }
        coVerify(exactly = 1) { getTransactionsUseCase() }
    }

    @Test
    fun checkFirstState_isLoading() = runTest {
        viewModel = TransactionsHistoryViewModel(
            networkStateProvider = networkStateProvider,
            syncTransactionHistoryUseCase = syncTransactionHistoryUseCase,
            getTransactionsUseCase = getTransactionsUseCase
        )
        assertTrue(viewModel.uiState.value is TransactionsHistoryUiState.Loading)
    }

    @Test
    fun refreshTransactionHistory_updateUiStateWithNewTransaction() = runTest {
        transactionRepository.transactions.clear()

        viewModel = TransactionsHistoryViewModel(
            networkStateProvider = networkStateProvider,
            syncTransactionHistoryUseCase = syncTransactionHistoryUseCase,
            getTransactionsUseCase = getTransactionsUseCase
        )

        networkStateProvider.setIsConnected(true)
        transactionRepository.transactions.add(mealTransaction)

        viewModel.refreshTransactionHistory()

        val successUiState = viewModel.uiState.first()
        assertTrue(successUiState is TransactionsHistoryUiState.Success)
        val transactions = (successUiState as TransactionsHistoryUiState.Success).transactions
        assertTrue(transactions.isNotEmpty())
        assertEquals(mealTransaction.id, transactions[0].id)

    }
}
