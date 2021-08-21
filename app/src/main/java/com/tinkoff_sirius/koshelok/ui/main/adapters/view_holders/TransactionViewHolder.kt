package com.tinkoff_sirius.koshelok.ui.main.adapters.view_holders

import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.databinding.ItemTransactionBinding
import com.tinkoff_sirius.koshelok.entities.TransactionType

import com.tinkoff_sirius.koshelok.ui.main.adapters.model.MainItem

class TransactionViewHolder(
    private val binding: ItemTransactionBinding,
    private val deleteCallback: (Long) -> Unit,
    private val editCallback: (MainItem) -> Unit
) : MainViewHolder(binding.root) {

    private var idTransaction: Long? = null

    override fun bind(data: MainItem) {
        if (data is MainItem.Transaction) {
            idTransaction = data.id
            with(binding) {
                val type = when (data.category.typeName) {
                    TransactionType.INCOME -> root.context.getString(R.string.income)
                    TransactionType.OUTCOME -> root.context.getString(R.string.outcome)
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