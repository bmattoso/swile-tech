package com.br.swile.tech.core.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter

@Composable
fun ImageLoader(
    modifier: Modifier = Modifier,
    contentDescription: String?,
    imageUrl: String,
    @DrawableRes defaultContentResource: Int,
    contentScale: ContentScale = ContentScale.Fit
) {
    val asyncPainter = rememberAsyncImagePainter(
        model = imageUrl,
        placeholder = painterResource(defaultContentResource),
        fallback = painterResource(defaultContentResource)
    )

    Image(
        modifier = modifier,
        painter = asyncPainter,
        contentDescription = contentDescription,
        contentScale = contentScale
    )
}
