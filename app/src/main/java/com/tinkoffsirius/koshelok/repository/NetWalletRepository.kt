package com.tinkoffsirius.koshelok.repository

import com.tinkoffsirius.koshelok.repository.entities.*
import io.reactivex.rxjava3.core.Single

class NetWalletRepository : WalletRepository {
    override fun getWalletById(id: Long, idUser: String, idToken: String): Single<WalletData> {
        TODO("Not yet implemented")
    }

    override fun deleteTransactionById(id: Long): Single<Response> {
        TODO("Not yet implemented")
    }

    override fun createTransaction(
        transactionData: CreateTransactionData,
        idUser: String,
        idToken: String
    ): Single<Response> {
        TODO("Not yet implemented")
    }

    override fun getCategories(
        transactionType: String,
        idUser: String,
        idToken: String
    ): Single<List<CategoryData>> {
        TODO("Not yet implemented")
    }

    override fun updateTransaction(
        transactionData: CreateTransactionData,
        idUser: String,
        idToken: String
    ): Single<Response> {
        TODO("Not yet implemented")
    }

    override fun createWallet(
        walletData: CreateWalletData,
        idUser: String,
        idToken: String
    ): Single<Response> {
        TODO("Not yet implemented")
    }

    override fun updateWallet(
        walletData: CreateWalletData,
        idUser: String,
        idToken: String
    ): Single<Response> {
        TODO("Not yet implemented")
    }

    override fun getUserInfoWallets(idUser: String, idToken: String): Single<UserInfoWallets> {
        TODO("Not yet implemented")
    }

    override fun deleteWalletById(id: Long, idUser: String, idToken: String): Single<Response> {
        TODO("Not yet implemented")
    }
}