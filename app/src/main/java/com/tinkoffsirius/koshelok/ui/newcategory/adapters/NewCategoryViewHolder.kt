package com.tinkoffsirius.koshelok.ui.newcategory.adapters

import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.databinding.ItemNewCategoryBinding
import com.tinkoffsirius.koshelok.entities.Icon



class NewCategoryViewHolder (itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    private val binding by viewBinding(ItemNewCategoryBinding::bind)

    var imgBack: ImageView? = null

    init {
        imgBack = binding.transactionCategoryImageBack
    }

    fun bind(icon: Icon) {

        binding.transactionCategoryImage.setImageResource(icon.imgId)
       imgBack?.backgroundTintList =
            ColorStateList.valueOf(binding.root.context.getColor(icon.colorId))

    }
}