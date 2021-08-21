package com.tinkoff_sirius.koshelok

import android.app.Application
import timber.log.Timber

class KoshelokApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        Dependencies.context = this
    }
}