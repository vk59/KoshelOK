package com.tinkoffsirius.koshelok.ui.main.adapters.model

import com.tinkoffsirius.koshelok.model.Category
import kotlinx.datetime.LocalDate

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
        val category: Category,
        val date: LocalDate,
        val time: String
    ) : MainItem()

    class Date(val date: LocalDate) : MainItem()
}
