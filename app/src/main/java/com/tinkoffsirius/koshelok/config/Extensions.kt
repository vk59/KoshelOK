package com.tinkoffsirius.koshelok.config

import com.tinkoffsirius.koshelok.Icons
import com.tinkoffsirius.koshelok.entities.Category
import com.tinkoffsirius.koshelok.entities.Currency
import com.tinkoffsirius.koshelok.entities.PosedTransaction
import com.tinkoffsirius.koshelok.entities.TransactionType
import com.tinkoffsirius.koshelok.repository.entities.CategoryData
import com.tinkoffsirius.koshelok.repository.entities.CreateTransactionData
import com.tinkoffsirius.koshelok.repository.entities.TransactionData
import com.tinkoffsirius.koshelok.ui.Colors
import com.tinkoffsirius.koshelok.ui.main.adapters.model.MainItem
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDateTime

fun PosedTransaction.toCreateTransactionData(
    walletId: Long,
    dateTime: String
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
    return Category(id, TransactionType.valueOf(categoryType), name,
        Icons.values().first { icon == it.id }.drawableId,
        Colors.values().first { color == it.id }.res
    )
}

fun Category.toCategoryData(creationDate: LocalDateTime, userId: Long): CategoryData {
    return CategoryData(
        id,
        categoryName,
        Colors.values().first { color == it.res }.id,
        Icons.values().first { icon == it.drawableId }.id,
        creationDate.toString(),
        typeName.name,
        userId
    )
}

fun TransactionData.toMainItemTransaction(): MainItem.Transaction {
    return MainItem.Transaction(
        id!!,
        amount,
        currency,
        category.toCategory(),
        date.toLocalDateTime().toLocalDate(),
        date.toLocalDateTime().toStringTime()
    )
}

fun LocalDateTime.toLocalDate(): LocalDate {
    return LocalDate(this.year, this.monthNumber, this.dayOfMonth)
}

fun LocalDateTime.toStringTime(): String {
    return if (this.minute > 10) "${this.hour}:${this.minute}" else "${this.hour}:0${this.minute}"
}