package com.tinkoff_sirius.koshelok.ui.transaction_category.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.model.Transaction

class TransactionCategoryViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private var mTransactionCategoryImage: ImageView? = null
    private var mTransactionCategoryTitle: TextView? = null

    init {
        mTransactionCategoryImage = itemView.findViewById(R.id.transaction_category_image)
        mTransactionCategoryTitle = itemView.findViewById(R.id.transaction_category_title)
    }

    fun bind(transaction: Transaction) {
        mTransactionCategoryImage?.setBackgroundResource(transaction.category.icon)
        mTransactionCategoryTitle?.text = transaction.category.type.nameType
    }
}