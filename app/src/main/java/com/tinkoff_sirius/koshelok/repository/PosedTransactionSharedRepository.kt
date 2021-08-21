package com.tinkoff_sirius.koshelok.repository

import android.content.SharedPreferences
import com.tinkoff_sirius.koshelok.entitis.PosedTransaction
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class PosedTransactionSharedRepository(private val sharedPreferences: SharedPreferences) {

    fun saveTransaction(posedTransaction: PosedTransaction) : Completable = Completable.fromCallable {
        val data = Json.encodeToString(posedTransaction)
        sharedPreferences.edit()
            .putString("transaction", data)
            .commit()
    }

    fun getTransaction(): Single<PosedTransaction> = Single.fromCallable {

        val data: String = sharedPreferences.getString(TRANSACTION_DATA, null)!!

        Json.decodeFromString(PosedTransaction.serializer(), data)
    }


    companion object {
        const val TRANSACTION_DATA = "transaction"
    }

}
