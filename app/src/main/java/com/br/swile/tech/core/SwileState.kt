package com.br.swile.tech.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberSwileState(
    networkState: NetworkState,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): SwileState {
    return remember(navController, coroutineScope, networkState) {
        SwileState(navController, coroutineScope, networkState)
    }
}

class SwileState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    networkState: NetworkState
) {
    val isOnline = false

    fun onBackPressed() {
        navController.popBackStack()
    }
}
