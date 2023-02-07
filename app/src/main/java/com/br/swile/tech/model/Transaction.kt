package com.br.swile.tech.model

import java.util.Date

data class Transaction(
    val id: String,
    val description: String,
    val type: TransactionType,
    val date: Date,
    val amount: Double,
    val currency: Currency,
    val smallIcon: Icon,
    val largeIcon: Icon,
)
