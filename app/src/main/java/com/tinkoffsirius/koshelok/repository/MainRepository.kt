package com.tinkoffsirius.koshelok.repository

import com.tinkoffsirius.koshelok.repository.entities.WalletData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface MainRepository {

    fun getWalletById(walletId: Long, userId: Long, googleToken: String): Single<WalletData>

    fun deleteTransactionById(idTransaction: Long): Completable
}
