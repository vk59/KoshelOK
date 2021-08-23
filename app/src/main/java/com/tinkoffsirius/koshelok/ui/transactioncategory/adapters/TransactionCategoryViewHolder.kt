package com.tinkoffsirius.koshelok.ui.transactioncategory.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.entities.Category

class TransactionCategoryViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private var transactionCategoryImage: ImageView? = null
    private var transactionCategoryTitle: TextView? = null
    var mTransactionImage: ImageView? = null

    init {
        // TODO: Binding
        transactionCategoryImage = itemView.findViewById(R.id.transaction_category_image)
        transactionCategoryTitle = itemView.findViewById(R.id.transaction_category_title)
        mTransactionImage = itemView.findViewById(R.id.selected_image)
    }

    fun bind(category: Category) {
        transactionCategoryImage?.setBackgroundResource(category.icon)
        transactionCategoryTitle?.text = category.categoryName
    }
}
