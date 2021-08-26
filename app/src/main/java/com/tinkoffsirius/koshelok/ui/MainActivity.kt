package com.tinkoffsirius.koshelok.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.tinkoffsirius.koshelok.R

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.navHostFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Koshelok)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        with(navController) {
            when (currentDestination) {
                graph.findNode(R.id.walletListFragment) -> finish()
                graph.findNode(R.id.mainFragment) ->
                    navigate(R.id.action_mainFragment_to_walletListFragment)
                else -> popBackStack()
            }
        }
    }
}
