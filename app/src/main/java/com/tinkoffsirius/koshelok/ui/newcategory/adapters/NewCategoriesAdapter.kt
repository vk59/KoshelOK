package com.tinkoffsirius.koshelok.ui.newcategory.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.entities.Icon

class NewCategoriesAdapter() :
    RecyclerView.Adapter<NewCategoryViewHolder>() {

    private var list: List<Icon> = listOf()

    var itemPosition: Int? = -1

    fun setData(data: List<Icon>) {
        list = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NewCategoryViewHolder(
            inflater.inflate(
                R.layout.item_transaction_category,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewCategoryViewHolder, position: Int) {
        val icon: Icon = list[position]

        if(itemPosition == position){
            holder.imgBack?.backgroundTintList =
                ColorStateList.valueOf(Color.RED)
        }


        holder.itemView.setOnClickListener {

            itemPosition = holder.absoluteAdapterPosition
          
            notifyDataSetChanged()

        }


        holder.bind(icon)
    }

    override fun getItemCount(): Int = list.size


}