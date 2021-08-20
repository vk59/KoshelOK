package com.tinkoff_sirius.koshelok.service

import com.tinkoff_sirius.koshelok.entities.Category
import com.tinkoff_sirius.koshelok.repository.entities.CreateResponse
import com.tinkoff_sirius.koshelok.repository.entities.TransactionData
import com.tinkoff_sirius.koshelok.repository.entities.WalletData
import io.reactivex.rxjava3.core.Single

object SampleNetworkService {
    fun getWalletById(id: Long, idUser: String, idToken: String): Single<WalletData> {
        return TODO()
    }

    fun createTransaction(
        transactionData: TransactionData,
        idUser: String,
        idToken: String
    ): Single<CreateResponse> {
        return TODO()
    }

    fun getCategories(
        transactionType: String,
        idUser: String,
        idToken: String
    ): Single<List<Category>> {
        return TODO()
    }
}