package com.tinkoffsirius.koshelok.ui.transactioncategory.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.entities.Category

class TransactionCategoryViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private var mTransactionCategoryImage: ImageView? = null
    private var mTransactionCategoryTitle: TextView? = null
    var mTransactionImage: ImageView? = null

    init {
        // TODO: Binding
        mTransactionCategoryImage = itemView.findViewById(R.id.transaction_category_image)
        mTransactionCategoryTitle = itemView.findViewById(R.id.transaction_category_title)
        mTransactionImage = itemView.findViewById(R.id.selected_image)
    }

    fun bind(category: Category) {
        mTransactionCategoryImage?.setBackgroundResource(category.icon)
        mTransactionCategoryTitle?.text = category.categoryName
    }
}
