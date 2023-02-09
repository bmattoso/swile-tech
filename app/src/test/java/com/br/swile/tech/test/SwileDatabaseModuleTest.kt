package com.br.swile.tech.test

import android.content.Context
import androidx.room.Room
import com.br.swile.tech.core.database.SwileDatabase
import com.br.swile.tech.transaction.local.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SwileDatabaseModuleTest {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): SwileDatabase {
        return Room.inMemoryDatabaseBuilder(context, SwileDatabaseTest::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideTransactionDao(
        swileDatabase: SwileDatabase
    ): TransactionDao = swileDatabase.transactionDao()
}
