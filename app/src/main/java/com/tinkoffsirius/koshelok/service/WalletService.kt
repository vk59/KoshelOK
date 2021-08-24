package com.tinkoffsirius.koshelok.service

import com.tinkoffsirius.koshelok.repository.entities.UserInfoWallets
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface WalletService {

    @GET("user/{id}")
    fun getUserInfoWallets(@Path("id") idUser: Long): Single<UserInfoWallets>
}
