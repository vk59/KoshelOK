package com.tinkoff_sirius.koshelok.repository

import com.tinkoff_sirius.koshelok.entities.Category
import com.tinkoff_sirius.koshelok.entities.Transaction
import com.tinkoff_sirius.koshelok.repository.entities.CreateResponse
import com.tinkoff_sirius.koshelok.repository.entities.WalletData
import io.reactivex.rxjava3.core.Single

class Repository {
    fun getWalletById(id: Long, idUser: String, idToken: String): Single<WalletData> {
        return TODO()
    }

    fun createTransaction(
        transaction: Transaction,
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