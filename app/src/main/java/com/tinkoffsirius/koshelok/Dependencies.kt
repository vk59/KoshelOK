package com.tinkoffsirius.koshelok

import android.content.Context
import com.tinkoffsirius.koshelok.repository.*
import com.tinkoffsirius.koshelok.ui.ResourceProvider
import com.tinkoffsirius.koshelok.ui.createwallet.CreateWalletViewModelFactory
import com.tinkoffsirius.koshelok.ui.main.MainViewModelFactory
import com.tinkoffsirius.koshelok.ui.newcategory.NewCategoryViewModelFactory
import com.tinkoffsirius.koshelok.ui.transactionediting.TransactionEditingViewModelFactory
import com.tinkoffsirius.koshelok.ui.walletlist.WalletsListViewModelFactory

object Dependencies {

    var context: Context? = null

    val walletRepository by lazy { WalletRepository() }

    val newCategoryRepository by lazy {
        NewCategorySharedRepository(
            SharedPreferencesFactory().create(
                context!!,
                SharedPreferencesFactory.NEW_CATEGORY_DATA
            )
        )
    }

    val newCategoryViewModelFactory by lazy {
        NewCategoryViewModelFactory(
            newCategorySharedRepository = newCategoryRepository
        )

    }

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
            transactionRepository = transactionRepository,
            repository = walletRepository
        )
    }

    val transactionRepository by lazy {
        PosedTransactionSharedRepository(
            SharedPreferencesFactory().create(
                context!!,
                SharedPreferencesFactory.TRANSACTION_DATA
            )
        )
    }

    val walletSharedRepository by lazy {
        WalletSharedRepository(
            SharedPreferencesFactory().create(
                context!!,
                SharedPreferencesFactory.WALLET_DATA
            )
        )
    }


    val createWalletViewModelFactory by lazy {
        CreateWalletViewModelFactory(
            walletSharedRepository = walletSharedRepository,
            accountRepository = accountRepository,
            repository = walletRepository
        )
    }

    val transactionViewModelFactory by lazy {
        TransactionEditingViewModelFactory(
            transactionRepository = transactionRepository,
            accountRepository = accountRepository,
            repository = walletRepository
        )
    }

    val walletListViewModelFactory by lazy {
        WalletsListViewModelFactory(
            accountRepository = accountRepository,
            repository = walletRepository,
            walletSharedRepository = walletSharedRepository
        )
    }

    val resourceProvider by lazy {
        ResourceProvider(context!!)
    }
}
