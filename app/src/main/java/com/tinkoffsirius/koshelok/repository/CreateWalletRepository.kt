package com.tinkoffsirius.koshelok.repository

import com.tinkoffsirius.koshelok.repository.entities.CreateWalletData
import io.reactivex.rxjava3.core.Single

interface CreateWalletRepository {

    fun createWallet(
        createWalletData: CreateWalletData,
        idToken: String
    ): Single<CreateWalletData>

    fun updateWallet(
        createWalletData: CreateWalletData,
        idToken: String
    ): Single<CreateWalletData>
}