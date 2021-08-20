package com.tinkoff_sirius.koshelok.ui.main.adapters.view_holders

import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoff_sirius.koshelok.databinding.ItemTransactionBinding
import com.tinkoff_sirius.koshelok.entitis.Category
import com.tinkoff_sirius.koshelok.ui.main.OptionsCallback
import com.tinkoff_sirius.koshelok.ui.main.adapters.model.MainItem

class TransactionViewHolder(private val itemView: View, private val callback: OptionsCallback) : MainViewHolder(itemView) {

    private val binding: ItemTransactionBinding by viewBinding(ItemTransactionBinding::bind)

    override fun bind(data: MainItem) {
        if (data is MainItem.Transaction) {
            val type = when (data.category) {
                is Category.Income -> "Пополнение"
                is Category.Outcome -> "Траты"
            }

            with(binding) {
                categoryTransaction.text = data.category.type
                moneyTransaction.text =
                    if (type == "Траты") "-${data.sum}" else data.sum.toString()
                typeTransaction.text = type
                timeTransaction.text = data.time
                iconTransaction.setImageResource(data.category.icon)
                iconTransactionBackground
                    .setBackgroundTintList(
                        root.getResources().getColorStateList(data.category.color)
                    )
                deleteButton.setOnClickListener {
                    callback.deleteItem(data)
                }
                editButton.setOnClickListener {
                    callback.editItem(data)
                }
            }
        }
    }
}
