package com.br.swile.tech.model

import androidx.compose.ui.graphics.Color
import com.br.swile.tech.core.theme.LightPurple
import com.br.swile.tech.core.theme.Purple
import com.br.swile.tech.core.util.DateExtension.formatDayMonth
import kotlinx.datetime.Instant
import java.text.NumberFormat
import java.util.Locale

data class Transaction(
    val id: String,
    val description: String,
    val type: TransactionType,
    val comment: String?,
    val date: Instant,
    val amount: Double,
    val currency: Currency,
    val smallIcon: Icon,
    val largeIcon: Icon,
) {
    val extraInformation: String
        get() = if (!comment.isNullOrBlank()) {
            "${date.formatDayMonth()}・$comment"
        } else {
            date.formatDayMonth()
        }

    val amountBackgroundColor: Color
        get() = if (amount <= 0) Color.White else LightPurple

    val amountFontColor: Color
        get() = if (amount <= 0) Color.Black else Purple

    val formattedAmount: String
        get() = if (amount > 0) {
            "+${getCurrencyFormatter().format(amount)}"
        } else {
            getCurrencyFormatter().format(amount)
        }

    private fun getCurrencyFormatter() = NumberFormat.getCurrencyInstance(Locale.FRANCE).apply {
        currency = java.util.Currency.getInstance(this@Transaction.currency.code)
        maximumFractionDigits = 2
    }
}
