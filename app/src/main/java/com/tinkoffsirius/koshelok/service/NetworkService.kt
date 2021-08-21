package com.tinkoffsirius.koshelok.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

object NetworkService {

    const val BASE_URL = "https://superservice.com"

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    private val retrofitBuilder = Retrofit.Builder()
        .client(okHttpClient)

    val searchApi: Api by lazy {
        retrofitBuilder
            .baseUrl(BASE_URL)
            .build()
            .create()
    }
}
