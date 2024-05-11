package com.byteme.fitness.data.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.byteme.fitness.FitnessDatabase

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            FitnessDatabase.Schema,
            context,
            "fitness.db"
        )
    }
}