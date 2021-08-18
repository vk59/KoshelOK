package com.tinkoff_sirius.koshelok.model

sealed class MainItem {

    class Header(
        val walletName: String,
        val currentMoney: String,
        val income: String,
        val outcome: String,
        maxOutcome: String?) : MainItem()

    data class Transaction(val string: String) : MainItem()
}
