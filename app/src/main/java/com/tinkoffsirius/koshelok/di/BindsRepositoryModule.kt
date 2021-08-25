package com.tinkoffsirius.koshelok.di

import com.tinkoffsirius.koshelok.repository.CreateWalletRepository
import com.tinkoffsirius.koshelok.repository.CreateWalletRepositoryImpl
import com.tinkoffsirius.koshelok.repository.MainRepository
import com.tinkoffsirius.koshelok.repository.MainRepositoryImpl
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
    fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

    @Mocked
    @Binds
    fun bindMockWalletRepository(mockWalletRepositoryImpl: MockWalletRepositoryImpl): WalletRepository
}