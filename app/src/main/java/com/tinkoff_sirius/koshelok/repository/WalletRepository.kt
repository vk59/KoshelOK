package com.tinkoff_sirius.koshelok.repository

import com.tinkoff_sirius.koshelok.repository.entities.CategoryData
import com.tinkoff_sirius.koshelok.repository.entities.CreateTransactionData
import com.tinkoff_sirius.koshelok.repository.entities.Response
import com.tinkoff_sirius.koshelok.repository.entities.WalletData
import com.tinkoff_sirius.koshelok.service.SampleNetworkService
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
}
