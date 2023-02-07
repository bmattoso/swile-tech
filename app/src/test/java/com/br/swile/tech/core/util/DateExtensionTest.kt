package com.br.swile.tech.core.util

import com.br.swile.tech.core.util.DateExtension.toDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.junit.Assert.assertEquals
import org.junit.Test

class DateExtensionTest {

    @Test
    fun responseDateString_parseToDate() {
        val date = "2021-03-07T14:04:45.000+02:00"
        val instantDate = date.toDate()
        val localDateTime = instantDate.toLocalDateTime(TimeZone.UTC)

        assertEquals(2021, localDateTime.year)
        assertEquals(3, localDateTime.monthNumber)
        assertEquals(7, localDateTime.dayOfMonth)
        assertEquals(12, localDateTime.hour)
        assertEquals(4, localDateTime.minute)
        assertEquals(45, localDateTime.second)
        assertEquals(0, localDateTime.nanosecond)
    }
}
