package com.tinkoffsirius.koshelok.ui.walletlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.tinkoffsirius.koshelok.databinding.ItemWalletBinding

class WalletRecyclerAdapter : RecyclerView.Adapter<WalletViewHolder>() {

    private val diff = AsyncListDiffer(this, WalletDiffUtils())

    init {
        setHasStableIds(true)
    }

    fun setData(data: List<WalletItem>) {
        diff.submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return WalletViewHolder(ItemWalletBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
        holder.bind(diff.currentList[position])
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