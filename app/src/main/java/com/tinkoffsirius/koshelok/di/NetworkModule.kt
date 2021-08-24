package com.tinkoffsirius.koshelok.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.tinkoffsirius.koshelok.service.WalletService
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit

@Module
class NetworkModule {

    private val BASE_URL = "http://34.88.29.129:9090/"

    private val contentType = "application/json".toMediaType()

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return Json.asConverterFactory(contentType)
    }

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun providesHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun providesWalletService(retrofit: Retrofit): WalletService {
        return retrofit.create(WalletService::class.java)
    }
}