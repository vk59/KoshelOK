package com.tinkoffsirius.koshelok.ui.walletlist

data class WalletItem(
    val id: Long?,
    val name: String,
    val balance: String,
    val currencyType: String
)