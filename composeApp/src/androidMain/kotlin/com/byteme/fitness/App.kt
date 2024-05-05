package com.byteme.fitness

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        androidContext = applicationContext
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var androidContext: Context
    }
}