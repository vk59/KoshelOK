package com.tinkoffsirius.koshelok.repository

import android.content.SharedPreferences
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.tinkoffsirius.koshelok.di.AccountShared
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AccountSharedRepository @Inject constructor(
    @AccountShared
    private val sharedPreferences: SharedPreferences
) {

    fun saveAccount(account: GoogleSignInAccount) = Completable.fromCallable {
        sharedPreferences.edit()
            .putString(ACCOUNT_ID, account.id)
            .putString(ACCOUNT_ID_TOKEN, account.idToken ?: "123")
            .apply()
    }

    fun saveAccountId(idUser: Long) = Completable.fromCallable {
        sharedPreferences.edit()
            .putLong(ACCOUNT_ID_USER, idUser)
            .apply()
    }

    fun getAccount(name: String = ACCOUNT_ID): Single<String> = Single.fromCallable {
        sharedPreferences.getString(name, null)!!
    }

    fun getAccountId() = Single.fromCallable {
        sharedPreferences.getLong(ACCOUNT_ID_USER, 0)
    }

    companion object {
        const val ACCOUNT_EMAIL = "account_email"
        const val ACCOUNT_ID = "account_id"
        const val ACCOUNT_ID_TOKEN = "account_id_token"
        const val ACCOUNT_ID_USER = "account_id_user"
    }
}
