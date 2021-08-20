package com.tinkoff_sirius.koshelok.ui.transaction_category.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.entitis.Transaction

class TransactionCategoryAdapter : RecyclerView.Adapter<TransactionCategoryViewHolder>() {

    private var list: MutableList<Transaction> = mutableListOf()

    fun setData(data: MutableList<Transaction>) {
       list = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionCategoryViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return TransactionCategoryViewHolder(inflater.inflate(R.layout.item_transaction_category, parent , false))
    }

    override fun onBindViewHolder(holder: TransactionCategoryViewHolder, position: Int) {
        val transaction: Transaction = list[position]
        holder.bind(transaction)
    }

    override fun getItemCount(): Int = list.size
}
