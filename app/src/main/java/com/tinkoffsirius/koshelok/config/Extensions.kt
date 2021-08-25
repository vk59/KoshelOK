package com.tinkoffsirius.koshelok.config

import com.tinkoffsirius.koshelok.entities.Category
import com.tinkoffsirius.koshelok.entities.Currency
import com.tinkoffsirius.koshelok.entities.PosedTransaction
import com.tinkoffsirius.koshelok.entities.TransactionType
import com.tinkoffsirius.koshelok.repository.entities.CategoryData
import com.tinkoffsirius.koshelok.repository.entities.CreateTransactionData
import kotlinx.datetime.LocalDateTime

fun PosedTransaction.toCreateTransactionData(
    walletId: Long,
    dateTime: LocalDateTime
): CreateTransactionData {
    return CreateTransactionData(
        id,
        walletId,
        sum,
        type,
        category.id!!,
        dateTime.toString(),
        Currency.RUB.name
    )
}

fun CategoryData.toCategory(): Category {
    return Category(id, TransactionType.valueOf(categoryType), name, icon, color)
}

fun Category.toCategoryData(creationDate: LocalDateTime, userId: Long): CategoryData {
    return CategoryData(
        id,
        categoryName,
        color,
        icon,
        creationDate.toString(),
        typeName.name,
        userId
    )
}