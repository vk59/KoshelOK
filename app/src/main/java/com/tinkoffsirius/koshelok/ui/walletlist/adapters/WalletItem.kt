package com.tinkoffsirius.koshelok.ui.walletlist.adapters

data class WalletItem(
    val id: Long?,
    val name: String,
    val balance: String,
    val limit: String,
    val currencyType: String,
)
