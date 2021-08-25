package com.tinkoffsirius.koshelok.ui.newcategory.adapters

import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.databinding.ItemNewCategoryBinding
import com.tinkoffsirius.koshelok.entities.Icon
import com.tinkoffsirius.koshelok.ui.ColorProvider

class NewCategoryViewHolder (itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    val binding by viewBinding(ItemNewCategoryBinding::bind)

    var selectionImage: ImageView? = null

    init {
        selectionImage = binding.imageIcon
    }

    fun bind(icon: Icon) {
        binding.transactionCategoryImage.setImageResource(icon.imgId)
        binding.transactionCategoryImageBack.backgroundTintList =
            ColorStateList.valueOf(binding.root.context.getColor(ColorProvider.getColorResId(icon.color)))
    }
}
