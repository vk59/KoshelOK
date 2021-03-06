package com.tinkoffsirius.koshelok.config

import com.tinkoffsirius.koshelok.entities.*
import com.tinkoffsirius.koshelok.repository.entities.CategoryData
import com.tinkoffsirius.koshelok.repository.entities.CreateTransactionData
import com.tinkoffsirius.koshelok.repository.entities.CreateWalletData
import com.tinkoffsirius.koshelok.repository.entities.TransactionData
import com.tinkoffsirius.koshelok.ui.main.adapters.model.MainItem
import com.tinkoffsirius.koshelok.utils.Colors
import com.tinkoffsirius.koshelok.utils.Icons
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDateTime

fun PosedTransaction.toCreateTransactionData(
    walletId: Long,
    dateTime: String,
    currencyType: Currency
): CreateTransactionData {
    return CreateTransactionData(
        id,
        walletId,
        sum,
        type,
        category.id!!,
        dateTime.toString(),
        currencyType.name
    )
}

fun CategoryData.toCategory(): Category {
    return Category(
        id, TransactionType.valueOf(categoryType), name,
        Icons.values().first { icon == it.id }.drawableId,
        Colors.values().first { color == it.id }.res
    )
}

fun Category.toCategoryData(
    creationDate: LocalDateTime,
    userId: Long,
    colorIsFromDrawable: Boolean = true
): CategoryData {
    val colorInt = if (colorIsFromDrawable) {
        Colors.values().first { color == it.res }.id
    } else {
        Colors.values().first { color == it.rgb }.id
    }
    return CategoryData(
        id = id,
        name = categoryName,
        color = colorInt,
        icon = Icons.values().first { icon == it.drawableId }.id,
        creationDate = creationDate.toString(),
        categoryType = typeName.name,
        userId = userId
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

fun NewWallet.toCreateWalletData(userId: Long)=
    CreateWalletData(
        id = id,
        userId = userId,
        name = name,
        balance = "0",
        limit = limit,
        currencyType = currencyType,
        hidden = false
    )
