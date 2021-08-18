package com.tinkoff_sirius.koshelok.ui.main.adapters.view_holders

import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoff_sirius.koshelok.databinding.ItemTransactionBinding
import com.tinkoff_sirius.koshelok.model.CategorySealed
import com.tinkoff_sirius.koshelok.ui.main.adapters.model.MainItem

class TransactionViewHolder(private val itemView: View) : MainViewHolder(itemView) {

    private val binding: ItemTransactionBinding by viewBinding(ItemTransactionBinding::bind)

    override fun bind(data: MainItem) {
        if (data is MainItem.Transaction) {
            val type = when (data.category) {
                is CategorySealed.Income -> "Пополнение"
                is CategorySealed.Outcome -> "Траты"
                else -> "???"
            }

            binding.categoryTransaction.text = data.category.type
            binding.moneyTransaction.text =
                if (type == "Траты") "-${data.sum}" else data.sum.toString()
            binding.typeTransaction.text = type
//            binding.timeTransaction.text = data.date
            binding.iconTransaction.setImageResource(data.category.icon)
            binding.iconTransactionBackground
                .setBackgroundTintList(
                    binding.root.getResources().getColorStateList(data.category.color)
                )
        }
    }
}
