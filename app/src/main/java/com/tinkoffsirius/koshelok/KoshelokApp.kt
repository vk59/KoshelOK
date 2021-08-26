package com.tinkoffsirius.koshelok

import android.app.Application
import android.content.Context
import com.tinkoffsirius.koshelok.di.components.AppComponent
import com.tinkoffsirius.koshelok.di.components.DaggerAppComponent
import timber.log.Timber

class KoshelokApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is KoshelokApp -> appComponent
        else -> applicationContext.appComponent
    }
