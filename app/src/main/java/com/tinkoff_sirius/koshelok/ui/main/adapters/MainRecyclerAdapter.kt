package com.tinkoff_sirius.koshelok.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.ui.main.adapters.model.MainItem
import com.tinkoff_sirius.koshelok.ui.main.adapters.view_holders.DateViewHolder
import com.tinkoff_sirius.koshelok.ui.main.adapters.view_holders.HeaderViewHolder
import com.tinkoff_sirius.koshelok.ui.main.adapters.view_holders.MainViewHolder
import com.tinkoff_sirius.koshelok.ui.main.adapters.view_holders.TransactionViewHolder

class MainRecyclerAdapter(private val callback: OptionsCallback) :
    RecyclerView.Adapter<MainViewHolder>() {

    fun setData(data: List<MainItem>) {
        diff.submitList(data)
    }

    private val diff = AsyncListDiffer(this, TransactionsDiffUtils())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_TRANSACTION -> TransactionViewHolder(
                inflater.inflate(R.layout.item_transaction, parent, false),
            callback
            )
            TYPE_HEADER ->  HeaderViewHolder(inflater.inflate(R.layout.item_home_header, parent, false))
            TYPE_DATE -> DateViewHolder(inflater.inflate(R.layout.item_date, parent, false))
            else -> @Suppress throw Exception("You should use Transaction and Header View Holder")
        }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(diff.currentList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return when (diff.currentList[position]) {
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
