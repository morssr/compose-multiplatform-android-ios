package com.mls.kmp.mor.nytnewskmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize().background(Color.Cyan)) {
        //TODO add content
        Button(modifier = Modifier.align(Alignment.Center), onClick = {
            println("Button clicked")
        }) {
            Text(text = "Click me")
        }
    }
}
