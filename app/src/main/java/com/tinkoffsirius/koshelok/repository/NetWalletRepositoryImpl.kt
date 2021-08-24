package com.tinkoffsirius.koshelok.repository

import com.tinkoffsirius.koshelok.repository.entities.*
import com.tinkoffsirius.koshelok.service.HttpClientFactory
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class NetWalletRepositoryImpl @Inject constructor() : WalletRepository {

    private val client = HttpClientFactory.walletService

    override fun getWalletById(id: Long, idUser: Long, idToken: String): Single<WalletData> {
        return client.getWalletById(id)
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

    override fun getUserInfoWallets(idUser: Long, idToken: String): Single<UserInfoWallets> {
        return client.getUserInfoWallets(idUser = idUser)
    }

    override fun deleteWalletById(id: Long, idUser: String, idToken: String): Single<Response> {
        TODO("Not yet implemented")
    }
}