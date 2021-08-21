package com.tinkoffsirius.koshelok.repository

import android.content.SharedPreferences
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

class AccountSharedRepository(private val sharedPreferences: SharedPreferences) {

    fun saveAccount(account: GoogleSignInAccount) {
        sharedPreferences.edit()
            .putString(ACCOUNT_ID, account.id)
            .putString(ACCOUNT_ID_TOKEN, account.idToken)
            .apply()
    }

    fun getAccount(name: String = ACCOUNT_ID): String {
        return sharedPreferences.getString(ACCOUNT_ID, null)!!
    }

    companion object {
        const val ACCOUNT_EMAIL = "account_email"
        const val ACCOUNT_ID = "account_id"
        const val ACCOUNT_ID_TOKEN = "account_id_token"
    }
}
