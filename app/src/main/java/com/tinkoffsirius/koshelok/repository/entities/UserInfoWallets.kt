package com.tinkoffsirius.koshelok.repository.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoWallets(
    @SerialName("balance")
    val overallBalance: String,
    @SerialName("income")
    val overallIncome: String,
    @SerialName("spending")
    val overallSpending: String,
    @SerialName("walletsList")
    val wallets: List<WalletDataItem>
)
