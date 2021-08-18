package com.tinkoff_sirius.koshelok.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.model.MainItem


class MainRecyclerAdapter() :
    RecyclerView.Adapter<MainViewHolder>() {

    fun setData(data: List<MainItem>) {
        diff.submitList(data)
    }

    private val diff = AsyncListDiffer(this, object : DiffUtil.ItemCallback<MainItem>() {
        override fun areItemsTheSame(oldItem: MainItem, newItem: MainItem): Boolean = oldItem === newItem

        override fun areContentsTheSame(
            oldItem: MainItem,
            newItem: MainItem
        ): Boolean = oldItem == newItem

    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            TYPE_TRANSACTION -> TransactionViewHolder(inflater.inflate(R.layout.transaction_item, parent, false))
            TYPE_HEADER ->  HeaderViewHolder(inflater.inflate(R.layout.item_home_header, parent, false))
            else -> throw Exception("You should youse Transaction and Header View Holder")
        }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        when(holder) {
            is TransactionViewHolder -> {
                holder.bind(Any())
            }
            is HeaderViewHolder -> {
                holder.bind(diff.currentList[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(diff.currentList[position]) {
            is MainItem.Header -> TYPE_HEADER
            is MainItem.Transaction -> TYPE_TRANSACTION
        }
    }

    override fun getItemCount(): Int = diff.currentList.size

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_TRANSACTION = 1
    }
}