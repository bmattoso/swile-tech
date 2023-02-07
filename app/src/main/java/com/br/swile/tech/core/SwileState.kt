package com.br.swile.tech.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

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
    val coroutineScope: CoroutineScope,
    networkStateProvider: NetworkStateProvider
) {
    val isOnline = false

    fun onBackPressed() {
        navController.popBackStack()
    }
}
