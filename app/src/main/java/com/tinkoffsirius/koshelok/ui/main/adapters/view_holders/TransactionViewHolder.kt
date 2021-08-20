package com.tinkoffsirius.koshelok.ui.main.adapters.view_holders

import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.databinding.ItemTransactionBinding
import com.tinkoffsirius.koshelok.model.CategoryType
import com.tinkoffsirius.koshelok.ui.main.adapters.model.MainItem

class TransactionViewHolder(
    private val binding: ItemTransactionBinding,
    private val deleteCallback: (MainItem) -> Unit,
    private val editCallback: (MainItem) -> Unit
) : MainViewHolder(binding.root) {

    override fun bind(data: MainItem) {
        if (data is MainItem.Transaction) {
            with(binding) {
                val type = when (data.category.typeName) {
                    CategoryType.OUTCOME -> root.context.getString(R.string.income)
                    CategoryType.INCOME -> root.context.getString(R.string.outcome)
                }

                categoryTransaction.text = data.category.categoryName
                moneyTransaction.text =
                    if (type == root.context.getString(R.string.outcome))
                        "-${data.sum}"
                    else
                        data.sum.toString()
                typeTransaction.text = type
                timeTransaction.text = data.time
                iconTransaction.setImageResource(data.category.icon)
                iconTransactionBackground
                    .setBackgroundTintList(
                        root.getResources().getColorStateList(data.category.color)
                    )
                deleteButton.setOnClickListener {
                    deleteCallback(data)
                }
                editButton.setOnClickListener {
                    editCallback(data)
                }
            }
        }
    }
}
