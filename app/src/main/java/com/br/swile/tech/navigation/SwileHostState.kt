package com.br.swile.tech.navigation

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.br.swile.tech.core.network.NetworkStateProvider
import com.br.swile.tech.core.SwileState
import com.br.swile.tech.core.rememberSwileState

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class
)
@Composable
fun SwileHostState(
    networkStateProvider: NetworkStateProvider,
    swileState: SwileState = rememberSwileState(networkStateProvider = networkStateProvider),
) {

    Scaffold() { padding ->
        SwileNavigation(
            navController = swileState.navController,
            onBackPressed = swileState::onBackPressed,
            modifier = Modifier
                .padding(padding)
                .consumedWindowInsets(padding)
        )
    }

}
