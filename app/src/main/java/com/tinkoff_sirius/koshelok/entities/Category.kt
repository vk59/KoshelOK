package com.tinkoff_sirius.koshelok.entities

class Category(val id: Long?, val typeName: TransactionType, val categoryName: String, val icon: Int, val color: Int)

enum class TransactionType {
    INCOME, OUTCOME
}
