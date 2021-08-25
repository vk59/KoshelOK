package com.tinkoffsirius.koshelok.repository.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateWalletData(
    @SerialName("id")
    val id: Long?,
    @SerialName("userId")
    val userId: Long,
    @SerialName("name")
    val name: String,
    @SerialName("balance")
    val balance: String,
    @SerialName("income")
    val income: String,
    @SerialName("spending")
    val spending: String,
    @SerialName("limit")
    val limit: String?,
    @SerialName("currencyType")
    val currencyType: String,
    @SerialName("hidden")
    val hidden: Boolean
)
