package com.mls.kmp.mor.nytnewskmp

import org.koin.core.context.startKoin

fun initKoin() {
    // start Koin
    val koinApp = startKoin {
        modules(appModule)
    }.koin
}