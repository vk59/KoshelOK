package com.tinkoffsirius.koshelok.repository.entities

data class TransactionData(
    @SerialName("id")
    val id: Long?,
    @SerialName("amount")
    val amount: String,
    @SerialName("transactionType")
    val transactionType: String,
    @SerialName("category")
    val category: CategoryData,
    @SerialName("date")
    val date: String,
    @SerialName("currencyType")
    val currency: String
)
