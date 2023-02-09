package com.br.swile.tech.test

import androidx.room.Database
import androidx.room.TypeConverters
import com.br.swile.tech.core.database.InstantConverter
import com.br.swile.tech.core.database.SwileDatabase
import com.br.swile.tech.transaction.local.TransactionEntity

@Database(
    entities = [TransactionEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(InstantConverter::class)
abstract class SwileDatabaseTest : SwileDatabase()