package com.tinkoffsirius.koshelok.repository

import com.tinkoffsirius.koshelok.repository.entities.CurrencyData
import com.tinkoffsirius.koshelok.repository.entities.UserInfoWallets
import com.tinkoffsirius.koshelok.service.WalletService
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WalletListRepositoryImpl @Inject constructor() : WalletListRepository {

    @Inject
    lateinit var walletService: WalletService

    override fun deleteWalletById(idWallet: Long): Completable {
        return walletService.deleteWalletById(idWallet)
    }

    override fun getUserInfoWallets(idUser: Long, idToken: String): Single<UserInfoWallets> {
        return walletService.getUserInfoWallets(idUser = idUser)
    }

    override fun getExchangeCurrency(): Single<CurrencyData> {
        return walletService.getExchangeCurrency()
    }
}
