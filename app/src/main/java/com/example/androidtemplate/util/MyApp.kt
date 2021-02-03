package com.example.androidtemplate.util

import android.app.Application
import androidx.viewbinding.BuildConfig
import timber.log.Timber

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}