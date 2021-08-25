package com.tinkoffsirius.koshelok.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        NetworkModule::class,
        ViewModelModule::class,
        BindsRepositoryModule::class,
        SharedPreferencesModule::class
    ]
)
interface AppComponent : InjectFragments {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun context(context: Context): Builder
    }
}
