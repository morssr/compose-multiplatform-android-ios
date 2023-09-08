package com.mls.kmp.mor.nytnewskmp.core.data

import android.content.Context
import com.mls.kmp.mor.nytnewskmp.database.NytDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

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