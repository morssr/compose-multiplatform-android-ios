package com.mls.kmp.mor.nytnewskmp.data

import org.koin.dsl.module

val dataModule = module {
    single<ProductsRepository> { ProductsRepositoryImpl(get(), get(), get()) }
}