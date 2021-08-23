package com.tinkoffsirius.koshelok.repository

import com.tinkoffsirius.koshelok.repository.entities.*
import com.tinkoffsirius.koshelok.service.SampleNetworkService
import io.reactivex.rxjava3.core.Single

class WalletRepository {

    fun getWalletById(id: Long, idUser: String, idToken: String): Single<WalletData> {
        return SampleNetworkService.getWalletById(id, idUser, idToken)
    }

    fun deleteTransactionById(id: Long): Single<Response> {
        return SampleNetworkService.deleteTransactionById(id)
    }

    fun createTransaction(
        transactionData: CreateTransactionData,
        idUser: String,
        idToken: String
    ): Single<Response> {
        return SampleNetworkService.createTransaction(transactionData, idUser, idToken)
    }

    fun getCategories(
        transactionType: String,
        idUser: String,
        idToken: String
    ): Single<List<CategoryData>> {
        return SampleNetworkService.getCategories(transactionType, idUser, idToken)
    }

    fun updateTransaction(
        transactionData: CreateTransactionData,
        idUser: String,
        idToken: String
    ): Single<Response> {
        return SampleNetworkService.updateTransaction(transactionData, idUser, idToken)
    }

    fun createWallet(walletData: CreateWalletData, idUser: String, idToken: String): Single<Response> {
        return Single.just(Response("Successfully saved wallet $walletData"))
    }

    fun updateWallet(walletData: CreateWalletData, idUser: String, idToken: String): Single<Response> {
        return Single.just(Response("Successfully updated wallet $walletData"))
    }

    fun getUserInfoWallets(idUser: String, idToken: String): Single<UserInfoWallets> {
        return SampleNetworkService.getUserInfoWallets(idUser, idToken)
    }

    fun deleteWalletById(id: Long, idUser: String, idToken: String): Single<Response> {
        return SampleNetworkService.deleteWalletById(id, idUser, idToken)
    }
}
