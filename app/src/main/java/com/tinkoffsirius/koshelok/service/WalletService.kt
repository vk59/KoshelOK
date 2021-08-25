package com.tinkoffsirius.koshelok.service

import com.tinkoffsirius.koshelok.repository.entities.CreateWalletData
import com.tinkoffsirius.koshelok.repository.entities.UserInfo
import com.tinkoffsirius.koshelok.repository.entities.UserInfoWallets
import com.tinkoffsirius.koshelok.repository.entities.WalletData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface WalletService {

    @GET("user/info/")
    fun getUserInfoWallets(@Header("id") idUser: Long): Single<UserInfoWallets>

    @GET("wallet/")
    fun getWalletById(@Header("id") id: Long): Single<WalletData>

    @DELETE("transaction/")
    fun deleteTransactionById(@Header("id") id: Long): Completable

    @GET("user/email/")
    fun getUserByEmail(@Header("email") email: String): Single<UserInfo>

    @GET("user/googleId/")
    fun getUserByIdToken(@Header("googleToken") googleToken: String): Single<UserInfo>

    @POST("user/")
    fun registerUser(@Body userInfo: UserInfo): Single<UserInfo>

    @POST("wallet/")
    fun createWallet(@Body createWalletData: CreateWalletData): Single<CreateWalletData>

    @PUT("wallet/")
    fun updateWallet(@Body createWalletData: CreateWalletData): Single<CreateWalletData>
}
