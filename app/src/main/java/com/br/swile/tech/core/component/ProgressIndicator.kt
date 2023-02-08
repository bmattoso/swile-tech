package com.br.swile.tech.core.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.br.swile.tech.R

@Composable
fun ProgressIndicator(
    modifier: Modifier = Modifier,
    loadingMessage: Int = R.string.loading,
    animationSize: Dp = 100.dp
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            DefaultLottieAnimation(
                animationResId = R.raw.finance_loading,
                modifier = Modifier.size(animationSize)
            )
            Text(
                text = stringResource(id = loadingMessage),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
