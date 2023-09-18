package com.mls.kmp.mor.nytnewskmp

import com.mls.kmp.mor.nytnewskmp.core.data.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformModule = module {

    single { DatabaseDriverFactory(context = androidContext()).create() }
}
