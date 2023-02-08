package com.br.swile.tech.core.util

import com.br.swile.tech.core.util.StringExtension.capitalize
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.text.DecimalFormat

object DateExtension {

    fun String.toDate(): Instant = Instant.parse(this)

    fun Instant.formatDayMonth(): String {
        // TODO get ZoneId from system, using +2 from France
        val localDateTime = this.toLocalDateTime(TimeZone.of("+2"))
        val month = localDateTime.month.toString().capitalize()
        return "${localDateTime.dayOfMonth} $month"
    }

    fun Instant.formatDayMonthTime(): String {
        // TODO get ZoneId from system, using +2 from France
        val localDateTime = this.toLocalDateTime(TimeZone.of("+2"))
        val month = localDateTime.month.toString().capitalize()
        val dayOfWeek = localDateTime.dayOfWeek.toString().capitalize()
        val twoDigitsFormat = DecimalFormat("00")
        val time = "${localDateTime.hour}:${twoDigitsFormat.format(localDateTime.minute)}"
        return "$dayOfWeek ${localDateTime.dayOfMonth} $month, $time"
    }
}
