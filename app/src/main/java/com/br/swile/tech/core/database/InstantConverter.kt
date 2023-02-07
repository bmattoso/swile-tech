package com.br.swile.tech.core.database

import androidx.room.TypeConverter
import kotlinx.datetime.Instant

class InstantConverter {

    @TypeConverter
    fun longToInstant(value: Long?): Instant? = value?.let(Instant::fromEpochMilliseconds)

    @TypeConverter
    fun instantToLong(instant: Instant?): Long? = instant?.toEpochMilliseconds()
}