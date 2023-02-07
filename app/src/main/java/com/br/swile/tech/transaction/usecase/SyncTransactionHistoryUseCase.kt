package com.br.swile.tech.transaction.usecase

import com.br.swile.tech.transaction.repository.TransactionRepository
import javax.inject.Inject

class SyncTransactionHistoryUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

    suspend operator fun invoke() {
        transactionRepository.syncTransactions()
    }
}
