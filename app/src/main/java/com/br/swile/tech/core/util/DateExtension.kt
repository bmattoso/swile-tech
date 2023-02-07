package com.br.swile.tech.core.util

import kotlinx.datetime.Instant

object DateExtension {

    fun String.toDate(): Instant = Instant.parse(this)
}
