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

    val mockWalletRepository by lazy { MockWalletRepository() }

    val netWalletRepository by lazy { NetWalletRepository() }

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
            repository = netWalletRepository
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
            repository = mockWalletRepository
        )
    }

    val transactionViewModelFactory by lazy {
        TransactionEditingViewModelFactory(
            transactionRepository = transactionRepository,
            accountRepository = accountRepository,
            repository = mockWalletRepository
        )
    }

    val walletListViewModelFactory by lazy {
        WalletsListViewModelFactory(
            accountRepository = accountRepository,
            repository = netWalletRepository,
            walletSharedRepository = walletSharedRepository
        )
    }

    val resourceProvider by lazy {
        ResourceProvider(context!!)
    }
}
