package com.tinkoff_sirius.koshelok.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.tinkoff_sirius.koshelok.R

class TransactionCategoryViewHolder(itemView: View) :
    MainViewHolder(itemView) {
    private var mTransactionCategoryImage: ImageView? = null
    private var mTransactionCategoryTitle: TextView? = null

    init {
        mTransactionCategoryImage = itemView.findViewById(R.id.transaction_category_image)
        mTransactionCategoryTitle = itemView.findViewById(R.id.transaction_category_title)
    }

    override fun bind(vararg data: Any) {
        mTransactionCategoryImage?.setBackgroundResource(R.drawable.ic_transaction_category)
        mTransactionCategoryTitle?.text = data.toString()

    }
}