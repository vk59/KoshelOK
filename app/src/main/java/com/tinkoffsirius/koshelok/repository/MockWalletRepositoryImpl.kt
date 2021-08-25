package com.tinkoffsirius.koshelok.repository

import com.tinkoffsirius.koshelok.repository.entities.*
import com.tinkoffsirius.koshelok.service.SampleNetworkService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MockWalletRepositoryImpl @Inject constructor() : WalletRepository {

    override fun getWalletById(id: Long, idUser: Long, idToken: String): Single<WalletData> {
        return SampleNetworkService.getWalletById(id, idUser.toString(), idToken)
    }

    override fun deleteTransactionById(id: Long): Single<Response> {
        return SampleNetworkService.deleteTransactionById(id)
    }

    override fun createTransaction(
        transactionData: CreateTransactionData,
        idUser: String,
        idToken: String
    ): Single<Response> {
        return SampleNetworkService.createTransaction(transactionData, idUser, idToken)
    }

    override fun getCategories(
        transactionType: String,
        idUser: String,
        idToken: String
    ): Single<List<CategoryData>> {
        return SampleNetworkService.getCategories(transactionType, idUser, idToken)
    }

    override fun updateTransaction(
        transactionData: CreateTransactionData,
        idUser: String,
        idToken: String
    ): Single<Response> {
        return SampleNetworkService.updateTransaction(transactionData, idUser, idToken)
    }

    override fun createWallet(
        walletData: CreateWalletData,
        idUser: String,
        idToken: String
    ): Single<CreateWalletData> {
        return Single.fromCallable {
            CreateWalletData(0,0,"","","","","", "",false)
        }
    }

    override fun updateWallet(
        walletData: CreateWalletData,
        idUser: String,
        idToken: String
    ): Single<CreateWalletData> {
        return Single.just(CreateWalletData(0, 0, "", "", "", "", "", "", false))
    }

    override fun getUserInfoWallets(idUser: Long, idToken: String): Single<UserInfoWallets> {
        return SampleNetworkService.getUserInfoWallets(idUser, idToken)
    }

    override fun deleteWalletById(id: Long, idUser: String, idToken: String): Single<Response> {
        return SampleNetworkService.deleteWalletById(id, idUser, idToken)
    }

    override fun getUserByEmail(email: String): Single<UserInfo> {
        TODO("Not yet implemented")
    }

    override fun getUserByIdToken(googleToken: String): Single<UserInfo> {
        TODO("Not yet implemented")
    }

    override fun registerUser(userInfo: UserInfo): Single<UserInfo> {
        TODO("Not yet implemented")
    }
}
