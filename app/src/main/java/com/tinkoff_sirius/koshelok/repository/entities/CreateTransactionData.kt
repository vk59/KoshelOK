package com.tinkoff_sirius.koshelok.repository.entities

data class CreateTransactionData(
    val id: Long?,
    val amount: String,
    val transactionType: String,
    val categoryId: Long,
    val date: String,
    val currency: String
)
