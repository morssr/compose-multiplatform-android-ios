package com.mls.kmp.mor.nytnewskmp.ui

import org.koin.dsl.module

val uiModule = module {
    single { HomeScreenModel(get(), get()) }
    factory { (productId: Int) ->
        ProductScreenModel(
            productId = productId,
            repository = get(),
            logger = get()
        )
    }
}