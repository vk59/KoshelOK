package com.tinkoff_sirius.koshelok.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.tinkoff_sirius.koshelok.R

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.navHostFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Koshelok)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        if (navController.previousBackStackEntry?.destination?.id != R.id.onBoardingFragment) {
            navController.popBackStack()
        }
    }
}
