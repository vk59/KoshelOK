package com.tinkoffsirius.koshelok.ui.main.adapters

import androidx.recyclerview.widget.DiffUtil
import com.tinkoffsirius.koshelok.ui.main.adapters.model.MainItem

class TransactionsDiffUtils : DiffUtil.ItemCallback<MainItem>() {

    override fun areItemsTheSame(oldItem: MainItem, newItem: MainItem): Boolean =
        oldItem === newItem

    override fun areContentsTheSame(
        oldItem: MainItem,
        newItem: MainItem
    ): Boolean = oldItem == newItem
}
