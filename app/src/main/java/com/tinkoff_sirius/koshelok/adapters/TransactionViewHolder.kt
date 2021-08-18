package com.tinkoff_sirius.koshelok.adapters

import android.view.View
import android.widget.TextView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.databinding.ItemTransactionBinding
import com.tinkoff_sirius.koshelok.model.MainItem

class TransactionViewHolder(private val itemView: View) : MainViewHolder(itemView) {

    private val binding: ItemTransactionBinding by viewBinding(ItemTransactionBinding::bind)

    override fun bind(data: MainItem) {
        itemView.findViewById<TextView>(R.id.title).text = data.toString()
    }
}
