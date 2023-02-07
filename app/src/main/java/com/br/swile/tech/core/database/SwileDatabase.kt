package com.br.swile.tech.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.br.swile.tech.transaction.local.TransactionDao
import com.br.swile.tech.transaction.local.TransactionEntity

@Database(
    entities = [TransactionEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(InstantConverter::class)
abstract class SwileDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}
