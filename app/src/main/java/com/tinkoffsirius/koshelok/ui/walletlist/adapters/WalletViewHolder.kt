package com.tinkoffsirius.koshelok.ui.walletlist.adapters

import androidx.recyclerview.widget.RecyclerView
import com.tinkoffsirius.koshelok.databinding.ItemWalletBinding

class WalletViewHolder(
    private val binding: ItemWalletBinding,
    private val deleteItem: (WalletItem) -> Unit,
    private val editItem: (WalletItem) -> Unit,
    private val watchItem: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(walletItem: WalletItem) {
        binding.balanceWallet.text = "${walletItem.balance} ${walletItem.currencyType}"
        binding.nameWallet.text = walletItem.name
        binding.deleteButton.setOnClickListener {
            deleteItem(walletItem)
        }
        binding.editButton.setOnClickListener {
            editItem(walletItem)
        }
        binding.watchButton.setOnClickListener {
            watchItem()
        }
    }
}
