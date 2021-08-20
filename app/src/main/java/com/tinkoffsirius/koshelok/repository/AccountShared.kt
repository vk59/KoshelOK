package com.tinkoffsirius.koshelok.repository

import android.content.SharedPreferences
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

class AccountShared(private val sharedPreferences: SharedPreferences) {

    fun saveAccount(account: GoogleSignInAccount) {
        sharedPreferences.edit()
                .putString(ACCOUNT_EMAIL, account.email)
                .apply()
    }

    fun getAccount(): String {
        return sharedPreferences.getString(ACCOUNT_EMAIL, null)!!
    }

    companion object {
        const val ACCOUNT_EMAIL = "account_email"
    }
}
