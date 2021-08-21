package com.tinkoffsirius.koshelok.entities

import kotlinx.serialization.Serializable

@Serializable
class Category(
    val id: Long?,
    val typeName: TransactionType,
    val categoryName: String,
    val icon: Int,
    val color: Int
)

@Serializable
enum class TransactionType {
    INCOME, OUTCOME
}

