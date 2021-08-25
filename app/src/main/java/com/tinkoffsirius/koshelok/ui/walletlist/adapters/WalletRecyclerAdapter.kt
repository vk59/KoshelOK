package com.tinkoffsirius.koshelok.ui.walletlist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.tinkoffsirius.koshelok.databinding.ItemWalletBinding

class WalletRecyclerAdapter(
    private val onItemClick: (WalletItem) -> Unit,
    private val deleteItem: (WalletItem) -> Unit,
    private val editItem: (WalletItem) -> Unit,
    private val watchItem: () -> Unit
) : RecyclerView.Adapter<WalletViewHolder>() {

    private val diff = AsyncListDiffer(this, WalletDiffUtils())

    init {
        setHasStableIds(true)
    }

    fun setData(data: List<WalletItem>) {
        diff.submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return WalletViewHolder(
            ItemWalletBinding.inflate(inflater, parent, false),
            deleteItem,
            editItem,
            watchItem
        )
    }

    override fun onViewRecycled(holder: WalletViewHolder) {
        super.onViewRecycled(holder)
        holder.reset()
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
        val walletItem = diff.currentList[position]
        holder.bind(walletItem)
        holder.itemView.setOnLongClickListener {
            onItemClick(walletItem)
            false
        }
    }

    override fun getItemViewType(position: Int): Int {
        return TYPE_WALLET
    }

    override fun getItemCount(): Int = diff.currentList.size

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_WALLET = 1
    }
}
