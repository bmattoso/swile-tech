package com.br.swile.tech.core.util

import java.util.Locale

object StringExtension {

    fun String.capitalize(): String = this.lowercase().replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }
}
