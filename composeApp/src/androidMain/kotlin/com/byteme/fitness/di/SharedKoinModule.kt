package com.byteme.fitness.di

import app.cash.sqldelight.db.SqlDriver
import com.byteme.fitness.App.Companion.androidContext
import com.byteme.fitness.data.database.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sqlDriverModule: Module = module {
    single<SqlDriver> {
        DatabaseDriverFactory(context = androidContext).create()
    }
}


