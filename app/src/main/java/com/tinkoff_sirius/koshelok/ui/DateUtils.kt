package com.tinkoff_sirius.koshelok.ui

import android.content.Context
import com.tinkoff_sirius.koshelok.R
import kotlinx.datetime.LocalDate

object DateUtils {
    fun toUIString(date: LocalDate, context: Context): String {
        val result = StringBuilder().append("${date.dayOfMonth} ")
        result.append(
            when (date.monthNumber) {
                1 -> context.getString(R.string.january)
                2 -> context.getString(R.string.february)
                3 -> context.getString(R.string.march)
                4 -> context.getString(R.string.april)
                5 -> context.getString(R.string.may)
                6 -> context.getString(R.string.june)
                7 -> context.getString(R.string.july)
                8 -> context.getString(R.string.august)
                9 -> context.getString(R.string.september)
                10 -> context.getString(R.string.october)
                11 -> context.getString(R.string.november)
                12 -> context.getString(R.string.december)
                else -> "???"
            }
        )
        return result.toString()
    }
}