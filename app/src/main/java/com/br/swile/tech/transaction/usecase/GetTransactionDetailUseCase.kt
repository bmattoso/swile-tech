package com.br.swile.tech.transaction.usecase

import com.br.swile.tech.model.Transaction
import com.br.swile.tech.transaction.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTransactionDetailUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

    operator fun invoke(transactionId: String?): Flow<Transaction?> =
        transactionRepository.getTransactionById(transactionId)
}
