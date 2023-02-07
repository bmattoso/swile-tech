package com.br.swile.tech.core.database

import android.content.Context
import androidx.room.Room
import com.br.swile.tech.transaction.local.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesNiaDatabase(
        @ApplicationContext context: Context,
    ): SwileDatabase = Room.databaseBuilder(
        context,
        SwileDatabase::class.java,
        "swile-database",
    ).build()

    @Provides
    fun providesTransactionDao(
        database: SwileDatabase
    ): TransactionDao = database.transactionDao()
}
