package com.tinkoffsirius.koshelok.service

import com.tinkoffsirius.koshelok.repository.entities.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface WalletService {

    @GET("user/info/")
    fun getUserInfoWallets(@Header("id") idUser: Long): Single<UserInfoWallets>

    @GET("wallet/details/")
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

    @POST("transaction/")
    fun createTransaction(@Body transactionData: CreateTransactionData): Completable

    @PUT("transaction/")
    fun updateTransaction(@Body transactionData: CreateTransactionData): Completable

    @GET("category/income/")
    fun getIncomeCategories(@Header("id") idUser: Long): Single<List<CategoryData>>

    @GET("category/outcome/")
    fun getSpendingCategories(@Header("id") idUser: Long): Single<List<CategoryData>>

    @POST("category/")
    fun createCategory(@Body categoryData: CategoryData): Completable

    @DELETE("wallet/")
    fun deleteWalletById(@Header("id") idWallet: Long): Completable

    @GET("rate/")
    fun getExchangeCurrency(): Single<CurrencyData>
}
