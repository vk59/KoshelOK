package com.tinkoffsirius.koshelok.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.tinkoffsirius.koshelok.databinding.ItemDateBinding
import com.tinkoffsirius.koshelok.databinding.ItemHomeHeaderBinding
import com.tinkoffsirius.koshelok.databinding.ItemTransactionBinding
import com.tinkoffsirius.koshelok.ui.ResourceProvider
import com.tinkoffsirius.koshelok.ui.main.adapters.model.MainItem
import com.tinkoffsirius.koshelok.ui.main.adapters.viewHolders.DateViewHolder
import com.tinkoffsirius.koshelok.ui.main.adapters.viewHolders.HeaderViewHolder
import com.tinkoffsirius.koshelok.ui.main.adapters.viewHolders.MainViewHolder
import com.tinkoffsirius.koshelok.ui.main.adapters.viewHolders.TransactionViewHolder

class MainRecyclerAdapter(
    private val deleteCallback: (Long) -> Unit,
    private val editCallback: (MainItem) -> Unit,
    private val resourceProvider: ResourceProvider
) : RecyclerView.Adapter<MainViewHolder>() {

    private val diff = AsyncListDiffer(this, TransactionsDiffUtils())
    private val HEADER_ID = -1L

    init {
        setHasStableIds(true)
    }

    fun setData(data: List<MainItem>) {
        diff.submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_TRANSACTION -> TransactionViewHolder(
                ItemTransactionBinding.inflate(inflater, parent, false),
                deleteCallback,
                editCallback,
                resourceProvider
            )
            TYPE_HEADER -> HeaderViewHolder(
                ItemHomeHeaderBinding.inflate(inflater, parent, false),
                resourceProvider
            )
            TYPE_DATE -> DateViewHolder(
                ItemDateBinding.inflate(inflater, parent, false),
                resourceProvider
            )
            else -> throw @Suppress Exception("You should use Transaction and Header View Holder")
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

    override fun getItemId(position: Int): Long {
        val mainItem = diff.currentList[position]
        return when (mainItem) {
            is MainItem.Date -> {
                val time = mainItem.date
                - (time.year * 10000 + time.monthNumber * 100 + time.dayOfMonth).toLong()
            }
            is MainItem.Header -> HEADER_ID
            is MainItem.Transaction -> mainItem.id
        }
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_TRANSACTION = 1
        private const val TYPE_DATE = 2
    }
}
