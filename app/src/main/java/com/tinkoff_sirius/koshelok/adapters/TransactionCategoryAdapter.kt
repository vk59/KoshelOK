package com.tinkoff_sirius.koshelok.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.model.MainAdapterItem

class TransactionCategoryAdapter() : RecyclerView.Adapter<TransactionCategoryViewHolder>() {

    private var list: MutableList<MainAdapterItem.Transaction> = mutableListOf()

    fun setData(data: MutableList<MainAdapterItem.Transaction>) {
       list = data
    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionCategoryViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return TransactionCategoryViewHolder(inflater.inflate(R.layout.transaction_category_item, parent , false))
    }

    override fun onBindViewHolder(holder: TransactionCategoryViewHolder, position: Int) {
        val transaction: MainAdapterItem.Transaction = list[position]
        holder.bind(transaction.title)
    }

    override fun getItemCount(): Int = list.size


}