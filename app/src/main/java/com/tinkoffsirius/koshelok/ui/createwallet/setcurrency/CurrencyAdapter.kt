package com.tinkoffsirius.koshelok.ui.createwallet.setcurrency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.tinkoffsirius.koshelok.databinding.ItemCurrencyBinding
import com.tinkoffsirius.koshelok.entities.Currency

class CurrencyAdapter(
    private val setSelected: (currency: Currency) -> Unit
) : RecyclerView.Adapter<CurrencyViewHolder>() {

    private var data: List<Currency> = listOf()

    private var selectedPosition: Int = -1

    fun setData(data: List<Currency>) {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(
            ItemCurrencyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.binding.selectedImage.isVisible = selectedPosition == position

        holder.itemView.setOnClickListener {
            selectedPosition = holder.absoluteAdapterPosition
            setSelected(data[position])
            notifyDataSetChanged()
        }

        holder.bind(data[position])
    }
}
