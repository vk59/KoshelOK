package com.tinkoffsirius.koshelok.repository.entities

data class WalletDataItem(
    val id: Long?,
    val name: String,
    val balance: String,
    val limit: String,
    val currencyType: String
)
