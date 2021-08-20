package com.tinkoff_sirius.koshelok.entities

import kotlinx.datetime.LocalDate

data class Transaction(
//    val sum: BigDecimal,
    val sum: Double,
    val category: Category,
    val date: LocalDate
)
