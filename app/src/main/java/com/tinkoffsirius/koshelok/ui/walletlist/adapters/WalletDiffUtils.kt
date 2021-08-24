package com.tinkoffsirius.koshelok.ui.walletlist.adapters

import androidx.recyclerview.widget.DiffUtil

class WalletDiffUtils : DiffUtil.ItemCallback<WalletItem>() {

    override fun areItemsTheSame(oldItem: WalletItem, newItem: WalletItem): Boolean =
        oldItem === newItem

    override fun areContentsTheSame(
        oldItem: WalletItem,
        newItem: WalletItem
    ): Boolean = oldItem == newItem
}
