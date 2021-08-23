package com.tinkoffsirius.koshelok.entities

import kotlinx.serialization.Serializable

@Serializable
data class PosedTransaction(
    val sum: String,
    val type: String,
    val category: Category,
    val id: Long? = null
)
