package com.tinkoff_sirius.koshelok.model

import kotlinx.datetime.LocalDate

class Transaction(
    sum: Double,
    type: TransactionType,
    date: LocalDate
)
