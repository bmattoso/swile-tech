package com.br.swile.tech.transaction.usecase

import com.br.swile.tech.model.Transaction
import com.br.swile.tech.transaction.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTransactionsUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke(): Flow<List<Transaction>> = transactionRepository.getTransactions()
}
