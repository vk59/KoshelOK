package com.tinkoffsirius.koshelok.di

import com.tinkoffsirius.koshelok.repository.MockWalletRepositoryImpl
import com.tinkoffsirius.koshelok.repository.NetWalletRepositoryImpl
import com.tinkoffsirius.koshelok.repository.WalletRepository
import dagger.Binds
import dagger.Module

@Module
interface BindsRepositoryModule {
    @Binds
    fun bindNetWalletRepository(netWalletRepositoryImpl: NetWalletRepositoryImpl): WalletRepository

    @Mocked
    @Binds
    fun bindMockWalletRepository(mockWalletRepositoryImpl: MockWalletRepositoryImpl): WalletRepository
}