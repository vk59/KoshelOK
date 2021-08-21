package com.tinkoffsirius.koshelok.ui.main.adapters.viewHolders

import android.content.res.ColorStateList
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.databinding.ItemTransactionBinding
import com.tinkoffsirius.koshelok.entities.TransactionType
import com.tinkoffsirius.koshelok.ui.ResourceProvider
import com.tinkoffsirius.koshelok.ui.main.adapters.model.MainItem

class TransactionViewHolder(
    private val binding: ItemTransactionBinding,
    private val deleteCallback: (Long) -> Unit,
    private val editCallback: (MainItem) -> Unit,
    private val resourceProvider: ResourceProvider
) : MainViewHolder(binding.root) {

    private var idTransaction: Long? = null

    override fun bind(data: MainItem) {
        if (data is MainItem.Transaction) {
            idTransaction = data.id
            with(binding) {
                val type = when (data.category.typeName) {
                    TransactionType.INCOME -> resourceProvider.getString(R.string.income)
                    TransactionType.OUTCOME -> resourceProvider.getString(R.string.outcome)
                }

                categoryTransaction.text = data.category.categoryName
                moneyTransaction.text =
                    if (type == root.context.getString(R.string.outcome)) "-" else {
                        "+"
                    } + "${data.sum} ${data.currency}"
                typeTransaction.text = type
                timeTransaction.text = data.time
                iconTransaction.setImageResource(data.category.icon)
                iconTransactionBackground
                    .setBackgroundTintList(
                        ColorStateList.valueOf(resourceProvider.getColor(data.category.color))
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
