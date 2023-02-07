package com.br.swile.tech.transaction.remote

import com.br.swile.tech.transaction.repository.TransactionRepository
import com.br.swile.tech.transaction.repository.TransactionRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
object TransactionModule {

    @Provides
    fun providesTransactionService(
        retrofit: Retrofit
    ): TransactionService = retrofit.create(TransactionService::class.java)

    @Provides
    fun providesTransactionDataApi(
        ioDispatcher: CoroutineDispatcher,
        transactionService: TransactionService
    ): TransactionDataApi = TransactionDataApiImpl(
        ioDispatcher = ioDispatcher,
        transactionService = transactionService
    )

    @Provides
    fun providesTransactionRepository(
        transactionDataApi: TransactionDataApi
    ): TransactionRepository = TransactionRepositoryImpl(
        transactionDataApi = transactionDataApi
    )
}
