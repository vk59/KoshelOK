package com.tinkoff_sirius.koshelok.ui.main.adapters.view_holders

import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoff_sirius.koshelok.databinding.ItemDateBinding
import com.tinkoff_sirius.koshelok.ui.main.adapters.model.MainItem

class DateViewHolder (view: View) : MainViewHolder(view) {

    private val binding: ItemDateBinding by viewBinding(ItemDateBinding::bind)

    override fun bind(data: MainItem) {
        if (data is MainItem.Date) {
            binding.dateTitle.text = data.date
        }
    }
}
