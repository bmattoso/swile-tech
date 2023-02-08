package com.br.swile.tech.transaction.usecase

import com.br.swile.tech.transaction.repository.TransactionRepository
import javax.inject.Inject

class GetTransactionDetailUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

}
