package com.br.swile.tech.core.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class LoadingState {

    private var isLoading = false
    private val loadingState = MutableStateFlow(isLoading)

    val state: Flow<Boolean> = loadingState

    fun addLoading() {
        isLoading = true
    }

    fun removeLoading() {
        isLoading = false
    }
}
