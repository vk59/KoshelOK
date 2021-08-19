package com.tinkoff_sirius.koshelok.ui.main.adapters.model

import com.tinkoff_sirius.koshelok.model.CategorySealed

sealed class MainItem {

    class Header(
        val walletName: String,
        val currentMoney: String,
        val income: String,
        val outcome: String,
        val maxOutcome: String?
        ) : MainItem()

    class Transaction(
        val sum: Int,
        val category: CategorySealed,
        val date: String,
        val time: String
    ) : MainItem()

    class Date(val date: String) : MainItem()
}
