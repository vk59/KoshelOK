package com.tinkoffsirius.koshelok.repository.shared

import android.content.SharedPreferences
import com.tinkoffsirius.koshelok.di.TransShared
import com.tinkoffsirius.koshelok.entities.Category
import com.tinkoffsirius.koshelok.entities.PosedTransaction
import com.tinkoffsirius.koshelok.entities.TransactionType
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class PosedTransactionSharedRepository @Inject constructor(
    @TransShared
    private val sharedPreferences: SharedPreferences
) {

    fun saveTransaction(posedTransaction: PosedTransaction): Completable =
        Completable.fromCallable {
            val data = Json.encodeToString(posedTransaction)
            sharedPreferences.edit()
                .putString(TRANSACTION_DATA, data)
                .apply()
        }

    fun getTransaction(): Single<PosedTransaction> = Single.fromCallable {
        val data = sharedPreferences.getString(TRANSACTION_DATA, null)
        if (data != null) {
            Json.decodeFromString(PosedTransaction.serializer(), data)
        } else {
            PosedTransaction("", "", Category(0, TransactionType.INCOME, "", 0, 0))
        }
    }

    fun removeTransaction() {
        sharedPreferences.edit()
            .clear()
            .apply()
    }

    companion object {
        const val TRANSACTION_DATA = "transaction"
    }
}
