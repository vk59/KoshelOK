package com.tinkoffsirius.koshelok.repository.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateTransactionData(
    @SerialName("id")
    val id: Long?,
    @SerialName("amount")
    val amount: String,
    @SerialName("transactionType")
    val transactionType: String,
    @SerialName("categoryId")
    val categoryId: Long,
    @SerialName("date")
    val date: String,
    @SerialName("currencyType")
    val currency: String
)
