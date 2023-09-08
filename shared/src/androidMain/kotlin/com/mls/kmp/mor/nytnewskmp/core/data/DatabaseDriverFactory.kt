package com.mls.kmp.mor.nytnewskmp.core.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.mls.kmp.mor.nytnewskmp.database.NytDatabase

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            NytDatabase.Schema,
            context,
            "nyt.db"
        )
    }
}