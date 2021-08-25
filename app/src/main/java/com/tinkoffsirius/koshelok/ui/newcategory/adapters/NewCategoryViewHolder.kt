package com.tinkoffsirius.koshelok.ui.newcategory.adapters

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.databinding.ItemNewCategoryBinding
import com.tinkoffsirius.koshelok.entities.Icon

class NewCategoryViewHolder (itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    val binding by viewBinding(ItemNewCategoryBinding::bind)

    var imgBack: ImageView? = null

    init {
        imgBack = binding.transactionCategoryImageBack
    }

    fun bind(icon: Icon) {
        binding.transactionCategoryImage.setImageResource(icon.imgId)
    }
}
