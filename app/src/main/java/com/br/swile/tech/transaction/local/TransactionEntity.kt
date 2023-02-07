package com.br.swile.tech.transaction.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction")
data class TransactionEntity(
    @PrimaryKey val id: String,
    val name: String
)
