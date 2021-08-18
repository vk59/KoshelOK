package com.tinkoff_sirius.koshelok.model

import kotlinx.datetime.LocalDate

data class Transaction(
    val sum: Double,
    val category: Category,
    val date: LocalDate
)
