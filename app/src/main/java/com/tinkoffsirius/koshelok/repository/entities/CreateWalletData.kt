package com.tinkoffsirius.koshelok.repository.entities

data class CreateWalletData(
    val id: Long?,
    val name: String,
    val limit: String,
    val currencyType: String,
)
