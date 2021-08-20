package com.tinkoff_sirius.koshelok.service

import com.tinkoff_sirius.koshelok.config.AppConfig
import com.tinkoff_sirius.koshelok.repository.entities.CategoryData
import com.tinkoff_sirius.koshelok.repository.entities.CreateTransactionData
import com.tinkoff_sirius.koshelok.repository.entities.Response
import com.tinkoff_sirius.koshelok.repository.entities.WalletData
import io.reactivex.rxjava3.core.Single
import timber.log.Timber

object SampleNetworkService {
    private var walletData = AppConfig.walletExample

    fun getWalletById(id: Long, idUser: String, idToken: String): Single<WalletData> {
        return Single.fromCallable {
            walletData
        }
    }

    fun createTransaction(
        transactionData: CreateTransactionData,
        idUser: String,
        idToken: String
    ): Single<Response> {
        return Single.fromCallable {
            Response("Successfully")
        }
    }

    fun getCategories(
        transactionType: String,
        idUser: String,
        idToken: String
    ): Single<List<CategoryData>> {
        return Single.fromCallable {
            AppConfig.categoriesExample
        }
    }

    fun deleteTransactionById(id: Long): Single<Response> {
        walletData =
            WalletData(
                1,
                "Мой кошелек",
                "966.21",
                "63423.32",
                "5423",
                "4000",
                "RUB",
                walletData.transactions.filterNot { it.id == id }
            )
        Timber.d(walletData.transactions.toString())
        return Single.fromCallable {
            Response("Successfully deleted item ${id}")
        }
    }
}