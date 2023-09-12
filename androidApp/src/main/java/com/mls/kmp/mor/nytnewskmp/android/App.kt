package com.mls.kmp.mor.nytnewskmp.android

import android.app.Application
import com.mls.kmp.mor.nytnewskmp.appModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}