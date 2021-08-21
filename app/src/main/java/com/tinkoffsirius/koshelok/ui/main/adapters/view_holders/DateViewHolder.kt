package com.tinkoffsirius.koshelok.ui.main.adapters.view_holders

import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.databinding.ItemDateBinding
import com.tinkoffsirius.koshelok.ui.DateUtils
import com.tinkoffsirius.koshelok.ui.ResourceProvider
import com.tinkoffsirius.koshelok.ui.main.adapters.model.MainItem
import kotlinx.datetime.*

class DateViewHolder(
    private val binding: ItemDateBinding,
    private val resourceProvider: ResourceProvider
) : MainViewHolder(binding.root) {

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
                resourceProvider.getString(R.string.today)
            DatePeriod(0, 0, 1) ->
                resourceProvider.getString(R.string.yesterday)
            else -> DateUtils.toUIString(this, binding.root.context)
        }
    }
}
