package com.tinkoff_sirius.koshelok.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

object AccountShared {
    private const val ACCOUNT_DATA = "account_data"
    private const val ACCOUNT_EMAIL = "account_email"

    private fun getPreferences(context: Context) : SharedPreferences {
        return EncryptedSharedPreferences.create(
            context,
            ACCOUNT_DATA,
            MasterKey.Builder(context).build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }


    fun saveAccount(account: GoogleSignInAccount, context: Context) {
        getPreferences(context).edit()
                .putString(ACCOUNT_EMAIL, account.email)
                .apply()
    }

    fun getAccountEmail(context: Context) : String {
        return getPreferences(context).getString(ACCOUNT_EMAIL, "") ?: "null"
    }
}