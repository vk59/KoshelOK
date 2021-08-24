package com.tinkoffsirius.koshelok.ui.newcategory.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.Dependencies
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.databinding.ItemNewCategoryBinding
import com.tinkoffsirius.koshelok.databinding.ItemTransactionCategoryBinding
import com.tinkoffsirius.koshelok.entities.Category
import com.tinkoffsirius.koshelok.ui.transactioncategory.adapters.TransactionCategoryViewHolder

class NewCategorysAdapter() : RecyclerView.Adapter<NewCategoryViewHolder>() {

    private var list: List<Category> = listOf()

    fun setData(data: List<Category>) {
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
        val category: Category = list[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int = list.size


}