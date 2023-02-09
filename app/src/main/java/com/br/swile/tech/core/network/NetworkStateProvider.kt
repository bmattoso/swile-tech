package com.br.swile.tech.core.network

import kotlinx.coroutines.flow.Flow

interface NetworkStateProvider {
    val isOnlineFlow: Flow<Boolean>
}
