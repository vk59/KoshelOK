package com.tinkoffsirius.koshelok.service

import com.tinkoffsirius.koshelok.repository.entities.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface Api {

    /*
    How to get it that user is already registered?
     */
    // TODO: http://34.88.29.129:9090/swagger-ui/
    @PUT("user")
    fun changeUserInfo(idUser: String): Single<Response>

    // TODO: http://34.88.29.129:9090/swagger-ui/
    @POST("user")
    fun registerNewUser(idUser: String): Single<Response>

    @GET("user/{id}")
    fun getUserInfoWallets(@Path("id") idUser: String): Single<UserInfoWallets>

    @DELETE("user/{id}")
    fun deleteUser(@Path("id") idUser: String, idToken: String): Single<Response>

    @DELETE("wallet/{id}")
    fun deleteWalletById(@Path("id") id: Long): Single<Response>

    @GET("transaction/{id}")
    fun getWalletById(@Path("id") id: Long): Single<WalletData>

    @POST("transaction")
    fun createTransaction(@Body transactionData: CreateTransactionData): Single<Response>

    @PUT("transaction")
    fun updateTransaction(@Body transactionData: CreateTransactionData): Single<Response>

    @GET("transaction/{id}")
    fun getTransactionById(@Path("id") transactionId: Long): Single<Response>

    @DELETE("transaction/{id}")
    fun deleteTransactionById(@Path("id") id: Long): Single<Response>

    @PUT("category")
    fun updateCategory(@Body category: CategoryData): Single<Response>

    @POST("category")
    fun addNewCategory(@Body newCategory: NewCategoryData): Single<Response>

    @DELETE("category/{id}")
    fun deleteCategoryById(@Path("id") id: Long): Single<Response>

    fun getCategories(
        transactionType: String,
        idUser: String,
        idToken: String
    ): Single<List<CategoryData>>
}
