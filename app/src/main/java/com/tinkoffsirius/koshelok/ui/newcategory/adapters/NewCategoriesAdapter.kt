package com.tinkoffsirius.koshelok.ui.newcategory.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.entities.Icon

class NewCategoriesAdapter(private val setSelected: (icon: Icon) -> Unit) :
    RecyclerView.Adapter<NewCategoryViewHolder>() {

    private var list: List<Icon> = listOf()

    fun setData(data: List<Icon>) {
        list = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NewCategoryViewHolder(
            inflater.inflate(
                R.layout.item_new_category,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewCategoryViewHolder, position: Int) {
        val icon: Icon = list[position]

        holder.selectionImage?.isVisible = NewCategorySelectedItem.itemNumber == position

        holder.itemView.setOnClickListener {
            NewCategorySelectedItem.itemNumber = holder.absoluteAdapterPosition
            setSelected(icon)
            notifyDataSetChanged()
        }

        holder.bind(icon)
    }

    override fun getItemCount(): Int = list.size
}

object NewCategorySelectedItem {
    var itemNumber: Int? = -1
}
