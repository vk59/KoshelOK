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

class MainRecyclerAdapter(
    private val deleteCallback: (Long) -> Unit,
    private val editCallback: (MainItem) -> Unit
) : RecyclerView.Adapter<MainViewHolder>() {

    private val diff = AsyncListDiffer(this, TransactionsDiffUtils())

    fun setData(data: List<MainItem>) {
        diff.submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_TRANSACTION -> TransactionViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false),
                deleteCallback, editCallback
            )
            TYPE_HEADER -> HeaderViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_home_header, parent, false)
            )
            TYPE_DATE ->  DateViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_date, parent, false)
            )
            else -> throw Error("You should use Transaction and Header View Holder")
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
