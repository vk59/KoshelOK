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
        val currentTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val currentDate =
            LocalDate(currentTime.year, currentTime.monthNumber, currentTime.dayOfMonth)
        transDate.forEach { (date, list) ->
            val dateText =
                when (currentDate.minus(date.toLocalDate())) {
                    DatePeriod(0, 0, 0) -> "Сегодня"
                    DatePeriod(0, 0, 1) -> "Вчера"
                    else -> toString(date.toLocalDate())
                }
            transItems.add(MainItem.Date(dateText))
            transItems.addAll(list)
        }

        transactions = transItems.toList()
    }


    private fun toString(localDate: LocalDate): String {
        val result = StringBuffer().append("${localDate.dayOfMonth} ")
        result.append(
            when (localDate.monthNumber) {
                1 -> "января"
                2 -> "февраля"
                3 -> "марта"
                4 -> "апреля"
                5 -> "мая"
                6 -> "июня"
                7 -> "июля"
                8 -> "августа"
                9 -> "сентября"
                10 -> "октября"
                11 -> "ноября"
                12 -> "декабря"
                else -> "???"
            }
        )
        return result.toString()
    }
}
