package com.mls.kmp.mor.nytnewskmp.android

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mls.kmp.mor.nytnewskmp.MyApp
import com.mls.kmp.mor.nytnewskmp.ui.theme.MyAppTheme

@Preview
@Composable
fun AppPreviewLightTheme() {
    MyAppTheme {
        MyApp(false)
    }
}

@Preview
@Composable
fun AppPreviewDarkTheme() {
    MyAppTheme {
        MyApp(true)
    }
}
