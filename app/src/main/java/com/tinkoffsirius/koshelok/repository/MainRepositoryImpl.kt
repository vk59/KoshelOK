package com.tinkoffsirius.koshelok.repository

import com.tinkoffsirius.koshelok.repository.entities.WalletData
import com.tinkoffsirius.koshelok.service.WalletService
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor() : MainRepository {

    @Inject
    lateinit var walletService: WalletService

    override fun getWalletById(
        walletId: Long,
        userId: Long,
        googleToken: String
    ): Single<WalletData> {
        return walletService.getWalletById(walletId)
    }

    override fun deleteTransactionById(idTransaction: Long): Completable {
        return walletService.deleteTransactionById(idTransaction)
    }
}
