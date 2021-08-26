package com.tinkoffsirius.koshelok.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.tinkoffsirius.koshelok.databinding.ItemDateBinding
import com.tinkoffsirius.koshelok.databinding.ItemHomeHeaderBinding
import com.tinkoffsirius.koshelok.databinding.ItemTransactionBinding
import com.tinkoffsirius.koshelok.ui.main.adapters.model.MainItem
import com.tinkoffsirius.koshelok.ui.main.adapters.viewholders.DateViewHolder
import com.tinkoffsirius.koshelok.ui.main.adapters.viewholders.HeaderViewHolder
import com.tinkoffsirius.koshelok.ui.main.adapters.viewholders.MainViewHolder
import com.tinkoffsirius.koshelok.ui.main.adapters.viewholders.TransactionViewHolder

class MainRecyclerAdapter(
    private val deleteCallback: (Long) -> Unit,
    private val editCallback: (MainItem) -> Unit
) : RecyclerView.Adapter<MainViewHolder>() {

    private val diff = AsyncListDiffer(this, TransactionsDiffUtils())

    private val headerId = -1L

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
                editCallback
            )
            TYPE_HEADER -> HeaderViewHolder(
                ItemHomeHeaderBinding.inflate(inflater, parent, false)
            )
            TYPE_DATE -> DateViewHolder(
                ItemDateBinding.inflate(inflater, parent, false)
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
        return when (val mainItem = diff.currentList[position]) {
            is MainItem.Date -> {
                val time = mainItem.date
                -(time.year * YEAR_C + time.monthNumber * MONTH_C + time.dayOfMonth).toLong()
            }
            is MainItem.Header -> headerId
            is MainItem.Transaction -> mainItem.id
        }
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_TRANSACTION = 1
        private const val TYPE_DATE = 2
        private const val YEAR_C = 10000
        private const val MONTH_C = 100
    }
}
