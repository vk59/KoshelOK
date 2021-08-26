package com.tinkoffsirius.koshelok.di.modules

import com.tinkoffsirius.koshelok.repository.*
import dagger.Binds
import dagger.Module

@Module
interface BindsRepositoryModule {

    @Binds
    fun bindCreateWalletRepository(createWalletRepositoryImpl: CreateWalletRepositoryImpl): CreateWalletRepository

    @Binds
    fun bindTransactionEditingRepository(transactionEditingRepositoryImpl: TransactionEditingRepositoryImpl): TransactionEditingRepository

    @Binds
    fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

    @Binds
    fun bindWalletListRepository(walletListRepositoryImpl: WalletListRepositoryImpl): WalletListRepository

    @Binds
    fun bindNewCategoryRepository(newCategoryRepositoryImpl: NewCategoryRepositoryImpl): NewCategoryRepository
}
