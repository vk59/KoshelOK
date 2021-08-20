package com.tinkoff_sirius.koshelok.repository

import com.tinkoff_sirius.koshelok.repository.entities.Response
import com.tinkoff_sirius.koshelok.repository.entities.WalletData
import io.reactivex.rxjava3.core.Single

interface WalletRepository {

    fun getWalletById(id: Long, idUser: String, idToken: String) : Single<WalletData>

    fun deleteTransactionById(id: Long, idUser: String, idToken: String) : Single<Response>
}