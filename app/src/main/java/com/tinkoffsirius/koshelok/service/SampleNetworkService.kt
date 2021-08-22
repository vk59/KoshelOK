package com.tinkoffsirius.koshelok.service

import com.tinkoffsirius.koshelok.config.AppConfig
import com.tinkoffsirius.koshelok.config.AppConfig.categoriesExample
import com.tinkoffsirius.koshelok.entities.Currency
import com.tinkoffsirius.koshelok.repository.entities.*
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
        val newTransactions = mutableListOf(
            TransactionData(
                2,
                transactionData.amount,
                transactionData.transactionType,
                categoriesExample.filter { it.id == transactionData.categoryId }[0],
                transactionData.date,
                Currency.RUB.name
            )
        )
        newTransactions.addAll(walletData.transactions)
        walletData =
            WalletData(
                1,
                "Мой кошелек",
                "966.21",
                "63423.32",
                "5423",
                "4000",
                "RUB",
                newTransactions
            )
        Timber.d(walletData.transactions.toString())
        return Single.fromCallable {
            Response("Successfully created transaction ${transactionData}")
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
            Response("Successfully deleted item $id")
        }
    }
}
