package com.br.swile.tech.transaction.remote.di

import com.br.swile.tech.transaction.remote.TransactionDataApi
import com.br.swile.tech.transaction.remote.TransactionDataApiImpl
import com.br.swile.tech.transaction.remote.TransactionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

@InstallIn(ActivityComponent::class)
@Module
object TransactionRemoteModule {

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
}
