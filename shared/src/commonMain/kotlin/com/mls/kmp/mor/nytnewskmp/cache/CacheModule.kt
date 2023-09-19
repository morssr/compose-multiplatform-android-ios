package com.mls.kmp.mor.nytnewskmp.cache

import com.mls.kmp.mor.nytnewskmp.database.AppDatabase
import database.Products
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

val cacheModule = module {
    single {
        AppDatabase(
            driver = get(),
            productsAdapter = Products.Adapter(listOfStringsAdapter)
            )
    }

    single<ProductsDao> { ProductsDaoImpl(
        dbInstance = get(),
        coroutineContext = Dispatchers.IO,
        logger = get()
    ) }
}