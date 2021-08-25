package com.tinkoffsirius.koshelok.repository.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
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
