package com.tinkoffsirius.koshelok.repository

import android.content.SharedPreferences
import com.tinkoffsirius.koshelok.entities.Currency
import com.tinkoffsirius.koshelok.entities.NewWallet
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class WalletSharedRepository(private val sharedPreferences: SharedPreferences) {

    fun saveWallet(newWallet: NewWallet): Completable =
        Completable.fromCallable {
            val data = Json.encodeToString(newWallet)
            sharedPreferences.edit()
                .putString(WALLET, data)
                .apply()
        }

    fun getWallet(): Single<NewWallet> = Single.fromCallable {
        val data = sharedPreferences.getString(WALLET, null)
        if (data != null) {
            Json.decodeFromString(NewWallet.serializer(), data)
        } else {
            NewWallet(null, "", "", Currency.RUB.name)
        }
    }

    fun removeWallet() {
        sharedPreferences.edit()
            .clear()
            .apply()
    }

    companion object {
        const val WALLET = "wallet"
    }
}