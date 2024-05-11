package com.byteme.fitness.data.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.byteme.fitness.FitnessDatabase

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(
            FitnessDatabase.Schema,
            "launches.db"
        )
    }
}