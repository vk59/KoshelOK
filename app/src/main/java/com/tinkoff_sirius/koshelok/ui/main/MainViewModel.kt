package com.tinkoff_sirius.koshelok.ui.main

import androidx.lifecycle.ViewModel
import com.tinkoff_sirius.koshelok.config.AppConfig
import com.tinkoff_sirius.koshelok.ui.main.adapters.model.MainItem
import kotlinx.datetime.*

class MainViewModel : ViewModel() {
    var transactions = listOf<MainItem>()
    init {
        val transItems = mutableListOf<MainItem>()
        val transDate = AppConfig.mainTransactionsExample.groupBy { it.date }
        val currentTime= Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val currentDate = LocalDate(currentTime.year, currentTime.monthNumber, currentTime.dayOfMonth)
        for (date: String in transDate.keys) {
            val dateText = 
                when (currentDate.minus(date.toLocalDate())) {
                    DatePeriod(0, 0, 0) -> "Сегодня"
                    DatePeriod(0, 0, 1) -> "Вчера"
                    else -> "${date.toLocalDate().dayOfMonth}.${date.toLocalDate().monthNumber}"
                }
            transItems.add(MainItem.Date(dateText))
            for (trans in transDate[date]!!) {
                transItems.add(trans)
            }
        }
        transactions = transItems.toList()
    }
}
