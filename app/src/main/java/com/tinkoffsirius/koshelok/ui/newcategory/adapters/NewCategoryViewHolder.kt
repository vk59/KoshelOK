package com.tinkoffsirius.koshelok.ui.newcategory.adapters

import android.content.res.ColorStateList
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.Dependencies
import com.tinkoffsirius.koshelok.databinding.ItemNewCategoryBinding
import com.tinkoffsirius.koshelok.entities.Category

class NewCategoryViewHolder (itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    private val binding by viewBinding(ItemNewCategoryBinding::bind)

    fun bind(category: Category) {

        binding.transactionCategoryImage.setImageResource(category.icon)
        binding.transactionCategoryImageBack.backgroundTintList =
            ColorStateList.valueOf(Dependencies.resourceProvider.getColor(category.color))

    }
}