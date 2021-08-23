package com.tinkoffsirius.koshelok.repository.entities

data class UserInfoWallets(
    val overallBalance: String,
    val overallIncome: String,
    val overallSpending: String,
    val wallets: List<WalletDataItem>
)
