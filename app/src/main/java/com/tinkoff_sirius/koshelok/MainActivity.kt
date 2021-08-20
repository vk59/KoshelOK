package com.tinkoff_sirius.koshelok

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.navHostFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Koshelok)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        navController.popBackStack()
    }
}
