package com.tinkoffsirius.koshelok

import android.app.Application
//import com.facebook.stetho.Stetho
import timber.log.Timber

class KoshelokApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        Dependencies.context = this

//        Stetho.initializeWithDefaults(this);
    }
}
