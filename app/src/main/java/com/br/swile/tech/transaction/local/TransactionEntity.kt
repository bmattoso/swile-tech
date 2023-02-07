package com.br.swile.tech.transaction.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.br.swile.tech.model.TransactionType
import kotlinx.datetime.Instant

@Entity(tableName = "transaction")
data class TransactionEntity(
    @PrimaryKey val id: String,
    val description: String,
    val date: Instant,
    val amount: Double,
    @ColumnInfo(name = "currency_code") val currencyCode: String,
    @ColumnInfo(name = "currency_name") val currencyName: String,
    @ColumnInfo(name = "currency_symbol") val currencySymbol: String,
    @ColumnInfo(name = "small_icon_url") val smallIconUrl: String?,
    @ColumnInfo(name = "small_icon_type") val smallIconType: TransactionType,
    @ColumnInfo(name = "large_icon_url") val largeIconUrl: String?,
    @ColumnInfo(name = "large_icon_type") val largeIconType: TransactionType,
    val type: TransactionType
)
