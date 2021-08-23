package com.tinkoffsirius.koshelok.repository

import android.content.SharedPreferences
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import io.reactivex.rxjava3.core.Single

class AccountSharedRepository(private val sharedPreferences: SharedPreferences) {

    fun saveAccount(account: GoogleSignInAccount) {
        sharedPreferences.edit()
            .putString(ACCOUNT_ID, account.id)
            .putString(ACCOUNT_ID_TOKEN, account.idToken)
            .apply()
    }

    fun getAccount(name: String = ACCOUNT_ID): Single<String> = Single.fromCallable {
        sharedPreferences.getString(name, null)!!
    }

    companion object {
        const val ACCOUNT_EMAIL = "account_email"
        const val ACCOUNT_ID = "account_id"
        const val ACCOUNT_ID_TOKEN = "account_id_token"
    }
}
