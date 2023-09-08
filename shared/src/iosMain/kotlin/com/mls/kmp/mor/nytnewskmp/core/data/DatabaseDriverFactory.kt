package com.mls.kmp.mor.nytnewskmp.core.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.mls.kmp.mor.nytnewskmp.database.NytDatabase

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(NytDatabase.Schema, "nyt.db")
    }
}