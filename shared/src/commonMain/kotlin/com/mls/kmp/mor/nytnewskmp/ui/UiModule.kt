package com.mls.kmp.mor.nytnewskmp.ui

import org.koin.dsl.module

val uiModule = module {
    single { HomeScreenViewModel(get()) }
}