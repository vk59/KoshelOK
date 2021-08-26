package com.tinkoffsirius.koshelok.repository

import com.tinkoffsirius.koshelok.repository.entities.CreateWalletData
import com.tinkoffsirius.koshelok.service.WalletService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CreateWalletRepositoryImpl @Inject constructor() : CreateWalletRepository {

    @Inject
    lateinit var walletService: WalletService

    override fun createWallet(
        createWalletData: CreateWalletData,
        idToken: String
    ): Single<CreateWalletData> {
        return walletService.createWallet(createWalletData = createWalletData)
    }

    override fun updateWallet(
        createWalletData: CreateWalletData,
        idToken: String
    ): Single<CreateWalletData> {
        return walletService.updateWallet(createWalletData = createWalletData)
    }
}
