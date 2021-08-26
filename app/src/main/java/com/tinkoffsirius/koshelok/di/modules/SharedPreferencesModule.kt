package com.tinkoffsirius.koshelok.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.tinkoffsirius.koshelok.di.AccountShared
import com.tinkoffsirius.koshelok.di.NewCategoryShared
import com.tinkoffsirius.koshelok.di.TransShared
import com.tinkoffsirius.koshelok.di.WalletShared
import com.tinkoffsirius.koshelok.repository.shared.SharedPreferencesFactory
import dagger.Module
import dagger.Provides

@Module
class SharedPreferencesModule {

    @WalletShared
    @Provides
    fun provideWalletSharedPreference(context: Context): SharedPreferences {
        return SharedPreferencesFactory().create(
            context,
            SharedPreferencesFactory.WALLET_DATA
        )
    }

    @TransShared
    @Provides
    fun providePosedTransactionSharedPreference(context: Context): SharedPreferences {
        return SharedPreferencesFactory().create(
            context,
            SharedPreferencesFactory.TRANSACTION_DATA
        )
    }

    @NewCategoryShared
    @Provides
    fun provideNewCategorySharedPreference(context: Context): SharedPreferences {
        return SharedPreferencesFactory().create(
            context,
            SharedPreferencesFactory.NEW_CATEGORY_DATA
        )
    }

    @AccountShared
    @Provides
    fun provideAccountSharedPreference(context: Context): SharedPreferences {
        return SharedPreferencesFactory().create(
            context,
            SharedPreferencesFactory.ACCOUNT_DATA
        )
    }
}
