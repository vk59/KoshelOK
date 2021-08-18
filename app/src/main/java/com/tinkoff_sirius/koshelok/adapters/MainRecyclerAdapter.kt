package com.tinkoff_sirius.koshelok.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.model.MainItem


class MainRecyclerAdapter() :
    RecyclerView.Adapter<MainViewHolder>() {

    fun setData(data: List<MainItem>) {
        diff.submitList(data)
    }

    private val diff = AsyncListDiffer(this, TransactionsDiffUtils())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            TYPE_TRANSACTION -> TransactionViewHolder(inflater.inflate(R.layout.item_transaction, parent, false))
            TYPE_HEADER ->  HeaderViewHolder(inflater.inflate(R.layout.item_home_header, parent, false))
            else -> throw Exception("You should youse Transaction and Header View Holder")
        }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(diff.currentList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return when(diff.currentList[position]) {
            is MainItem.Header -> TYPE_HEADER
            is MainItem.Transaction -> TYPE_TRANSACTION
            is MainItem.Date -> TYPE_DATE
        }
    }

    override fun getItemCount(): Int = diff.currentList.size

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_TRANSACTION = 1
        private const val TYPE_DATE = 2
    }
}
