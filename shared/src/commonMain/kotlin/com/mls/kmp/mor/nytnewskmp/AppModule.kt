package com.mls.kmp.mor.nytnewskmp

import org.koin.dsl.module

val mainModule = module {
    single { Greeting() }
}

val appModule = module {
    includes(mainModule)
}