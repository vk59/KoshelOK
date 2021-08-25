package com.tinkoffsirius.koshelok.repository.main

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
        idUser: Long,
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
        idUser: Long,
        idToken: String
    ): Single<Response> {
        TODO("Not yet implemented")
    }

    override fun createWallet(
        walletData: CreateWalletData,
        idToken: String
    ): Single<CreateWalletData> {
       return client.createWallet(walletData)
    }

    override fun updateWallet(
        walletData: CreateWalletData,
        idToken: String
    ): Single<CreateWalletData> {
        TODO("Not yet implemented")
    }

    override fun getUserInfoWallets(idUser: Long, idToken: String): Single<UserInfoWallets> {
        return client.getUserInfoWallets(idUser = idUser)
    }

    override fun deleteWalletById(id: Long, idUser: String, idToken: String): Single<Response> {
        TODO("Not yet implemented")
    }

    override fun getUserByEmail(email: String): Single<UserInfo> {
        return client.getUserByEmail(email)
    }

    override fun getUserByIdToken(googleToken: String): Single<UserInfo> {
        TODO("Not yet implemented")
    }

    override fun registerUser(userInfo: UserInfo): Single<UserInfo> {
        return client.registerUser(userInfo)
    }
}