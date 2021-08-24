package com.tinkoffsirius.koshelok.service

import com.tinkoffsirius.koshelok.repository.entities.UserInfoWallets
import com.tinkoffsirius.koshelok.repository.entities.WalletData
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header

interface WalletService {

    @GET("user/info/")
    fun getUserInfoWallets(@Header("id") idUser: Long): Single<UserInfoWallets>

    @GET("wallet/")
    fun getWalletById(@Header("id") id: Long): Single<WalletData>
}
