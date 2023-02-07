package com.br.swile.tech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.br.swile.tech.core.NetworkState
import com.br.swile.tech.core.theme.SwileTheme
import com.br.swile.tech.navigation.SwileHostState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwileTheme {
                SwileHostState(
                    networkState = NetworkState()
                )
            }
        }
    }
}
