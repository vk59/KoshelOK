package com.tinkoff_sirius.koshelok.ui.main.adapters.view_holders

import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.databinding.ItemDateBinding
import com.tinkoff_sirius.koshelok.ui.DateUtils
import com.tinkoff_sirius.koshelok.ui.main.adapters.model.MainItem
import kotlinx.datetime.*

class DateViewHolder(private val binding: ItemDateBinding) : MainViewHolder(binding.root) {

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
            else -> DateUtils.toUIString(this, binding.root.context)
        }
    }
}
