package com.tinkoffsirius.koshelok.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

object HttpClientFactory {

    const val BASE_URL = "https://superservice.com"

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    private val retrofitBuilder = Retrofit.Builder()
        .client(okHttpClient)

    val walletService: WalletService by lazy {
        retrofitBuilder
            .baseUrl(BASE_URL)
            .build()
            .create()
    }
}
