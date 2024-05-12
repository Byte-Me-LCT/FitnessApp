package com.byteme.fitness.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import app.cash.sqldelight.db.SqlDriver
import com.byteme.fitness.data.database.DatabaseDriverFactory
import com.byteme.fitness.data.datastore.dataStore
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<SqlDriver> {
        DatabaseDriverFactory().create()
    }

    single<DataStore<Preferences>> {
        dataStore()
    }
}