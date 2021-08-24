package com.tinkoffsirius.koshelok.entities

import kotlinx.serialization.Serializable

@Serializable
data class NewWallet(
    val id: Long?,
    val name: String,
    val limit: String,
    val currencyType: String
)
