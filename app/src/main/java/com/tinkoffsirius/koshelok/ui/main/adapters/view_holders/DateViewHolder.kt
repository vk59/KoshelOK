package com.tinkoffsirius.koshelok.ui.main.adapters.view_holders

import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.databinding.ItemDateBinding
import com.tinkoffsirius.koshelok.ui.main.adapters.model.MainItem
import kotlinx.datetime.*

class DateViewHolder(view: View) : MainViewHolder(view) {

    private val binding: ItemDateBinding by viewBinding(ItemDateBinding::bind)

    override fun bind(data: MainItem) {
        if (data is MainItem.Date) {
            binding.dateTitle.text = data.date.toUIString()
        }
    }

    private fun LocalDate.toUIString() : String {
        val currentTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val currentDate =
            LocalDate(currentTime.year, currentTime.monthNumber, currentTime.dayOfMonth)
        return when (currentDate.minus(this)) {
            DatePeriod(0, 0, 0) ->
                binding.root.context.getString(R.string.today)
            DatePeriod(0, 0, 1) ->
                binding.root.context.getString(R.string.yesterday)
            else -> toString(this)
        }
    }

    private fun toString(localDate: LocalDate): String {
        val result = StringBuilder().append("${localDate.dayOfMonth} ")
        result.append(
            binding.root.run {
                when (localDate.monthNumber) {
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
            }
        )
        return result.toString()
    }

}
