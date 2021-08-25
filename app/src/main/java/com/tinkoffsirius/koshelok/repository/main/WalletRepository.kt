package com.tinkoffsirius.koshelok.repository.main

import com.tinkoffsirius.koshelok.repository.entities.*
import io.reactivex.rxjava3.core.Single

interface WalletRepository {

    fun getWalletById(id: Long, idUser: Long, idToken: String): Single<WalletData>

    fun deleteTransactionById(id: Long): Single<Response>

    fun createTransaction(
        transactionData: CreateTransactionData,
        idUser: Long,
        idToken: String
    ): Single<Response>

    fun getCategories(
        transactionType: String,
        idUser: String,
        idToken: String
    ): Single<List<CategoryData>>

    fun updateTransaction(
        transactionData: CreateTransactionData,
        idUser: Long,
        idToken: String
    ): Single<Response>

    fun createWallet(
        walletData: CreateWalletData,
        idToken: String
    ): Single<CreateWalletData>

    fun updateWallet(
        walletData: CreateWalletData,
        idToken: String
    ): Single<CreateWalletData>

    fun getUserInfoWallets(idUser: Long, idToken: String): Single<UserInfoWallets>

    fun deleteWalletById(id: Long, idUser: String, idToken: String): Single<Response>

    fun getUserByEmail(email: String): Single<UserInfo>

    fun getUserByIdToken(googleToken: String): Single<UserInfo>

    fun registerUser(userInfo: UserInfo): Single<UserInfo>
}