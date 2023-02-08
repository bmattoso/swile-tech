package com.br.swile.tech.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.br.swile.tech.core.network.NetworkStateProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberSwileState(
    networkStateProvider: NetworkStateProvider,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): SwileState {
    return remember(navController, coroutineScope, networkStateProvider) {
        SwileState(navController, coroutineScope, networkStateProvider)
    }
}

class SwileState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
    networkStateProvider: NetworkStateProvider
) {

    val isConnected = networkStateProvider.isOnlineFlow.stateIn(
        scope = coroutineScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = false
    )

    fun onBackPressed() {
        navController.popBackStack()
    }
}
