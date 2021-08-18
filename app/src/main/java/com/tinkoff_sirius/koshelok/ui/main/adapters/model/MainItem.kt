package com.tinkoff_sirius.koshelok.ui.main.adapters.model

sealed class MainItem {

    class Header(
        val walletName: String,
        val currentMoney: String,
        val income: String,
        val outcome: String,
        val maxOutcome: String?
        ) : MainItem()

    data class Transaction(val title: String) : MainItem()

    class Date(val date: String) : MainItem()
}
