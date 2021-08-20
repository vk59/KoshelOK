package com.tinkoffsirius.koshelok.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.databinding.ItemHomeHeaderBinding
import com.tinkoffsirius.koshelok.databinding.ItemTransactionBinding
import com.tinkoffsirius.koshelok.ui.main.adapters.model.MainItem
import com.tinkoffsirius.koshelok.ui.main.adapters.view_holders.DateViewHolder
import com.tinkoffsirius.koshelok.ui.main.adapters.view_holders.HeaderViewHolder
import com.tinkoffsirius.koshelok.ui.main.adapters.view_holders.MainViewHolder
import com.tinkoffsirius.koshelok.ui.main.adapters.view_holders.TransactionViewHolder

class MainRecyclerAdapter(
    private val deleteCallback: (MainItem) -> Unit,
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
                ItemTransactionBinding.inflate(inflater),
                deleteCallback, editCallback
            )
            TYPE_HEADER -> HeaderViewHolder(
                ItemHomeHeaderBinding.inflate(inflater)
            )
            TYPE_DATE -> DateViewHolder(inflater.inflate(R.layout.item_date, parent, false))
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
