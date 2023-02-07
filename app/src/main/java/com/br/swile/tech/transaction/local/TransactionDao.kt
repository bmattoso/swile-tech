package com.br.swile.tech.transaction.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Query(value = "SELECT * FROM `transaction`")
    fun getTransactionEntities(): Flow<List<TransactionEntity>>
}
