package com.br.swile.tech.transaction.di

import com.br.swile.tech.transaction.local.TransactionDao
import com.br.swile.tech.transaction.remote.TransactionDataApi
import com.br.swile.tech.transaction.remote.di.TransactionRemoteModule
import com.br.swile.tech.transaction.repository.TransactionRepository
import com.br.swile.tech.transaction.repository.TransactionRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module(includes = [TransactionRemoteModule::class])
@InstallIn(ActivityComponent::class)
object TransactionModule {

    @Provides
    fun providesTransactionRepository(
        transactionDataApi: TransactionDataApi,
        transactionDao: TransactionDao
    ): TransactionRepository = TransactionRepositoryImpl(
        transactionDataApi = transactionDataApi,
        transactionDao = transactionDao
    )
}
