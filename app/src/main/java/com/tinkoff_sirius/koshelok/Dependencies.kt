package com.tinkoff_sirius.koshelok

import android.content.Context
import com.tinkoff_sirius.koshelok.repository.AccountSharedRepository
import com.tinkoff_sirius.koshelok.repository.SharedPreferencesFactory
import com.tinkoff_sirius.koshelok.repository.WalletRepository
import com.tinkoff_sirius.koshelok.ui.ResourceProvider
import com.tinkoff_sirius.koshelok.ui.main.MainViewModelFactory

object Dependencies {

    var context: Context? = null

    val walletRepository by lazy { WalletRepository() }

    val accountRepository by lazy {
        AccountSharedRepository(
            SharedPreferencesFactory().create(
                context!!,
                SharedPreferencesFactory.ACCOUNT_DATA
            )
        )
    }

    val mainViewModelFactory by lazy {
        MainViewModelFactory(
            accountRepository = accountRepository,
            repository = walletRepository
        )
    }

    val resourceProvider by lazy {
        ResourceProvider(context!!)
    }
}