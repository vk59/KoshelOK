package com.tinkoff_sirius.koshelok.repository

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.security.crypto.MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE
import androidx.security.crypto.MasterKey.DEFAULT_MASTER_KEY_ALIAS
import com.google.android.gms.auth.api.signin.GoogleSignInAccount


object AccountShared {

    private const val ACCOUNT_DATA = "account_data"
    private const val ACCOUNT_EMAIL = "account_email"

    private fun getPreferences(context: Context) : SharedPreferences {
        val spec = KeyGenParameterSpec.Builder(
            DEFAULT_MASTER_KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(DEFAULT_AES_GCM_MASTER_KEY_SIZE)
            .build()

        val masterKey = MasterKey.Builder(context)
            .setKeyGenParameterSpec(spec)
            .build()

        return EncryptedSharedPreferences.create(
            context,
            ACCOUNT_DATA,
            masterKey,
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
        return getPreferences(context).getString(ACCOUNT_EMAIL, null)!!
    }
}
