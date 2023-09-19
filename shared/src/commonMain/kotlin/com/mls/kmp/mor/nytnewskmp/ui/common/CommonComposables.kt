package com.mls.kmp.mor.nytnewskmp.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.http.Url

@Composable
fun ProductImage(
    url: String,
    contentDescription: String = "unknown",
    modifier: Modifier = Modifier
) {
    KamelImage(
        resource = asyncPainterResource(Url(url)),
        contentDescription = "image of $contentDescription",
        modifier = modifier,
        contentScale = ContentScale.Crop,
        onLoading = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .shimmerBackground()
            )
        }
    )
}

@Composable
fun ProductScreenError(message: String = "Error") {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = message)
    }
}

@Composable
fun ProductScreenLoading() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}