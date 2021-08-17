package com.tinkoff_sirius.koshelok.ui

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class MainViewModel : ViewModel() {
    var account: GoogleSignInAccount? = null

    fun getSignInIntent(context: Context): Intent {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(context, gso)

        return mGoogleSignInClient.signInIntent
    }
}