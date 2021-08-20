package com.tinkoff_sirius.koshelok.ui.main.adapters.view_holders

import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.databinding.ItemTransactionBinding
import com.tinkoff_sirius.koshelok.entities.TransactionType
import com.tinkoff_sirius.koshelok.ui.main.adapters.model.MainItem

class TransactionViewHolder(
    private val view: View,
    private val deleteCallback: (Long) -> Unit,
    private val editCallback: (MainItem) -> Unit
) : MainViewHolder(view) {

    private var idTransaction: Long? = null

    private val binding: ItemTransactionBinding by viewBinding(ItemTransactionBinding::bind)

    override fun bind(data: MainItem) {
        if (data is MainItem.Transaction) {
            idTransaction = data.id
            with(binding) {
                val type = when (data.category.typeName) {
                    TransactionType.OUTCOME -> root.context.getString(R.string.income)
                    TransactionType.INCOME -> root.context.getString(R.string.outcome)
                }

                categoryTransaction.text = data.category.categoryName
                moneyTransaction.text =
                    if (type == root.context.getString(R.string.outcome)) "-" else { "+" } + "${data.sum} ${data.currency}"
                typeTransaction.text = type
                timeTransaction.text = data.time
                iconTransaction.setImageResource(data.category.icon)
                iconTransactionBackground
                    .setBackgroundTintList(
                        root.getResources().getColorStateList(data.category.color)
                    )
                deleteButton.setOnClickListener {
                    deleteCallback(idTransaction!!)
                }
                editButton.setOnClickListener {
                    editCallback(data)
                }
            }
        }
    }
}
