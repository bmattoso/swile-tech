package com.br.swile.tech.transaction.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.br.swile.tech.model.TransactionType

@Entity(tableName = "transaction")
data class TransactionEntity(
    @PrimaryKey val id: String,
    val description: String,
    val date: String,
    val amount: Double,
    val currencyCode: String,
    val currencyName: String,
    val currencySymbol: String,
    val smallIconUrl: String?,
    val smallIconType: TransactionType,
    val largeIconUrl: String?,
    val largeIconType: TransactionType,
    val type: TransactionType
)
