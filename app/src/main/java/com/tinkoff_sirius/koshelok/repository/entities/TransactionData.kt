package com.tinkoff_sirius.koshelok.repository.entities

data class TransactionData(
    val id: Long?,
    val amount: String,
    val transactionType: String,
    val category: CategoryData,
    val date: String,
    val currency: String
)
