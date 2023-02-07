package com.br.swile.tech.model

import kotlinx.datetime.Instant

data class Transaction(
    val id: String,
    val description: String,
    val type: TransactionType,
    val date: Instant,
    val amount: Double,
    val currency: Currency,
    val smallIcon: Icon,
    val largeIcon: Icon,
)
