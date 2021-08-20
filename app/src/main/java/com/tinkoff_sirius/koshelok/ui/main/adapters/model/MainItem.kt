package com.tinkoff_sirius.koshelok.ui.main.adapters.model

import com.tinkoff_sirius.koshelok.entities.Category
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
        val id: Long?,
        val sum: String,
        val currency: String,
        val category: Category,
        val date: LocalDate,
        val time: String
    ) : MainItem()

    class Date(val date: LocalDate) : MainItem()
}
