package com.tinkoffsirius.koshelok.model

class Category(val typeName: CategoryType, val categoryName: String, val icon: Int, val color: Int)

enum class CategoryType {
    INCOME, OUTCOME
}
