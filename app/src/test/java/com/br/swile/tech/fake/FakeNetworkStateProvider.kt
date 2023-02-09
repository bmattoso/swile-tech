package com.br.swile.tech.fake

import com.br.swile.tech.core.network.NetworkStateProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeNetworkStateProvider : NetworkStateProvider {

    private val mutableStateFlow = MutableStateFlow(false)

    override val isOnlineFlow: Flow<Boolean> = mutableStateFlow

    fun setIsConnected(isConnected: Boolean) {
        mutableStateFlow.value = isConnected
    }
}
