package com.tinkoffsirius.koshelok.ui.createwallet.setcurrency

import androidx.recyclerview.widget.RecyclerView
import com.tinkoffsirius.koshelok.databinding.ItemCurrencyBinding
import com.tinkoffsirius.koshelok.entities.Currency

class CurrencyViewHolder(
    val binding: ItemCurrencyBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(currency: Currency) {
        binding.currencyTitle.text = "${currency.string} (${currency.name})"
    }
}