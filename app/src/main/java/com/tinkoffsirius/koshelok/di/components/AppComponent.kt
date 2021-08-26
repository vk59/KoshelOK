package com.tinkoffsirius.koshelok.di.components

import android.content.Context
import com.tinkoffsirius.koshelok.di.modules.BindsRepositoryModule
import com.tinkoffsirius.koshelok.di.modules.NetworkModule
import com.tinkoffsirius.koshelok.di.modules.SharedPreferencesModule
import com.tinkoffsirius.koshelok.di.modules.ViewModelModule
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
