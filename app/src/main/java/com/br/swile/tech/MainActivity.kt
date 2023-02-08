package com.br.swile.tech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.br.swile.tech.core.network.NetworkStateProvider
import com.br.swile.tech.core.theme.SwileTheme
import com.br.swile.tech.navigation.SwileHostState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkStateProvider: NetworkStateProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            SwileTheme {
                SwileHostState(networkStateProvider = networkStateProvider)
            }
        }
    }
}
