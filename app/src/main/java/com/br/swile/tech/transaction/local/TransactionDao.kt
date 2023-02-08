package com.br.swile.tech.transaction.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Query(value = "SELECT * FROM `transaction`")
    fun getTransactionEntities(): Flow<List<TransactionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactionList(transactionList: List<TransactionEntity>)

    @Query(value = "SELECT * FROM `transaction` WHERE id = :transactionId")
    fun getTransactionById(transactionId: String?): Flow<TransactionEntity?>
}
