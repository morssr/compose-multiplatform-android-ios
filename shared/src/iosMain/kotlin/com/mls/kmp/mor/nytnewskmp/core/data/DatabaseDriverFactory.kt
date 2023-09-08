package com.mls.kmp.mor.nytnewskmp.core.data

import com.mls.kmp.mor.nytnewskmp.database.NytDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(NytDatabase.Schema, "nyt.db")
    }
}