package com.tinkoff_sirius.koshelok.repository.entities

class WalletData(
    val id: Long,
    val name: String,
    val balance: String,
    val income: String,
    val spending: String,
    val limit: String,
    val currencyType: String,
    val transactions: List<TransactionData>
)
