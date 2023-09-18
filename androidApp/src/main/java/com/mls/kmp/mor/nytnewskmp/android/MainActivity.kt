package com.mls.kmp.mor.nytnewskmp.android

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.mls.kmp.mor.nytnewskmp.MyApp

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        //fix for "Please use androidContext() function in your KoinApplication configuration."
//        val prod: ProductsDao = get()

        setContent {
            val darkTheme = isSystemInDarkTheme()

            val view = LocalView.current
            if (!view.isInEditMode) {
                SideEffect {
                    val window = (view.context as Activity).window
                    window.statusBarColor = Color.Transparent.toArgb()
                    window.navigationBarColor = Color.Transparent.toArgb()
                    WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                        !darkTheme
                    WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars =
                        !darkTheme
                }
            }

            MyApp(darkTheme)
        }
    }
}
