package com.tinkoffsirius.koshelok.ui.walletlist.adapters

import androidx.recyclerview.widget.RecyclerView
import com.tinkoffsirius.koshelok.databinding.ItemWalletBinding

class WalletViewHolder(
    private val binding: ItemWalletBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(walletItem: WalletItem) {
        binding.balanceWallet.text = "${walletItem.balance} ${walletItem.currencyType}"
        binding.nameWallet.text = walletItem.name
    }
}
