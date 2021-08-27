package com.tinkoffsirius.koshelok.repository

import com.tinkoffsirius.koshelok.repository.entities.CurrencyData
import com.tinkoffsirius.koshelok.repository.entities.UserInfoWallets
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface WalletListRepository {

    fun deleteWalletById(idWallet: Long): Completable

    fun getUserInfoWallets(idUser: Long, idToken: String): Single<UserInfoWallets>

    fun getExchangeCurrency(): Single<CurrencyData>
}
