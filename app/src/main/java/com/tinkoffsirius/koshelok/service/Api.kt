package com.tinkoffsirius.koshelok.service

import com.tinkoffsirius.koshelok.repository.entities.Response
import com.tinkoffsirius.koshelok.repository.entities.WalletData
import io.reactivex.rxjava3.core.Single
import retrofit2.http.DELETE
import retrofit2.http.GET


interface Api {
    @GET()
    fun getWalletById(id: Long, idUser: String, idToken: String): Single<WalletData>

    @DELETE
    fun deleteTransactionById(id: Long, idUser: String, idToken: String): Single<Response>
}
