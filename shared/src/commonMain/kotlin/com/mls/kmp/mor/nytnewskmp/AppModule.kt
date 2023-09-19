package com.mls.kmp.mor.nytnewskmp

import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.platformLogWriter
import com.mls.kmp.mor.nytnewskmp.api.apiModule
import com.mls.kmp.mor.nytnewskmp.cache.cacheModule
import com.mls.kmp.mor.nytnewskmp.data.dataModule
import com.mls.kmp.mor.nytnewskmp.ui.uiModule
import org.koin.core.module.Module
import org.koin.dsl.module

val mainModule = module {
    val baseLogger = Logger(
        config = StaticConfig(
            logWriterList = listOf(platformLogWriter()),
            minSeverity = Severity.Verbose
        ), "MyAppTag"
    )

    factory { baseLogger }
}

val appModule = module {
    includes(mainModule, platformModule, apiModule, cacheModule, dataModule, uiModule)
}

expect val platformModule: Module