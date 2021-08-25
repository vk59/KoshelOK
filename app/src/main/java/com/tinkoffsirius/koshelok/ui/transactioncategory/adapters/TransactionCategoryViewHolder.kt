package com.tinkoffsirius.koshelok.ui.transactioncategory.adapters

import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.databinding.ItemTransactionCategoryBinding
import com.tinkoffsirius.koshelok.entities.Category

class TransactionCategoryViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    var selectionImage: ImageView? = null

    private val binding by viewBinding(ItemTransactionCategoryBinding::bind)

    init {
        selectionImage = binding.selectedImage
    }

    fun bind(category: Category) {
        binding.transactionCategoryImage.setImageResource(category.icon)
        binding.transactionCategoryImageBack.backgroundTintList =
            ColorStateList.valueOf(binding.root.context.getColor(category.color))
        binding.transactionCategoryTitle.text = category.categoryName
    }
}
