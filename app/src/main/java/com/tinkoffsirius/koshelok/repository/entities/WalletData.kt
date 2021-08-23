package com.tinkoffsirius.koshelok.repository.entities

// TODO: Swagger
// CreateWalletData.
data class WalletData(
    val id: Long?,
    val name: String,
    val balance: String,
    val income: String,
    val spending: String,
    val limit: String,
    val currencyType: String,
    val transactions: List<TransactionData>
)
