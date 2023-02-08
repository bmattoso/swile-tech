package com.br.swile.tech.transaction.usecase

import com.br.swile.tech.core.util.OperationStatus
import com.br.swile.tech.transaction.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SyncTransactionHistoryUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

    suspend operator fun invoke(): Flow<OperationStatus> = flow<OperationStatus> {
        transactionRepository.syncTransactionsRemote()
        emit(OperationStatus.Success)
    }.catch { throwable: Throwable ->
        emit(OperationStatus.Failed(throwable))
    }
}
