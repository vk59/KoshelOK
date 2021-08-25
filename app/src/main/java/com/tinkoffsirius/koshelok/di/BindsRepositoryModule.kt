package com.tinkoffsirius.koshelok.di

import com.tinkoffsirius.koshelok.repository.*
import com.tinkoffsirius.koshelok.repository.main.MockWalletRepositoryImpl
import com.tinkoffsirius.koshelok.repository.main.NetWalletRepositoryImpl
import com.tinkoffsirius.koshelok.repository.main.WalletRepository
import dagger.Binds
import dagger.Module

@Module
interface BindsRepositoryModule {
    @Binds
    fun bindNetWalletRepository(netWalletRepositoryImpl: NetWalletRepositoryImpl): WalletRepository

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

    @Mocked
    @Binds
    fun bindMockWalletRepository(mockWalletRepositoryImpl: MockWalletRepositoryImpl): WalletRepository
}