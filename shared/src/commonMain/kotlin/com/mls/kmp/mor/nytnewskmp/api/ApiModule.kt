package com.mls.kmp.mor.nytnewskmp.api

import org.koin.dsl.module

val apiModule = module {
    single { ApiClient().client }
    single<ProductsApi> { ProductsApiImpl(client = get(), logger = get()) }
}