package com.tinkoffsirius.koshelok.repository

import com.tinkoffsirius.koshelok.repository.entities.*
import io.reactivex.rxjava3.core.Single

interface WalletRepository {

    fun getWalletById(id: Long, idUser: String, idToken: String): Single<WalletData>

    fun deleteTransactionById(id: Long): Single<Response>

    fun createTransaction(
        transactionData: CreateTransactionData,
        idUser: String,
        idToken: String
    ): Single<Response>

    fun getCategories(
        transactionType: String,
        idUser: String,
        idToken: String
    ): Single<List<CategoryData>>

    fun updateTransaction(
        transactionData: CreateTransactionData,
        idUser: String,
        idToken: String
    ): Single<Response>
    fun createWallet(walletData: CreateWalletData, idUser: String, idToken: String): Single<Response>
    fun updateWallet(walletData: CreateWalletData, idUser: String, idToken: String): Single<Response>

    fun getUserInfoWallets(idUser: Long, idToken: String): Single<UserInfoWallets>

    fun deleteWalletById(id: Long, idUser: String, idToken: String): Single<Response>
}