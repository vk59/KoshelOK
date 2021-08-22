package com.tinkoffsirius.koshelok.entities

import kotlinx.datetime.LocalDate

data class Transaction(
//    val sum: BigDecimal,
    val sum: String,
    val category: Category,
    val date: LocalDate,
    val currency: String = Currency.RUB.name
)
