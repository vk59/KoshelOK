package com.tinkoff_sirius.koshelok.entities

import kotlinx.serialization.Serializable

@Serializable
data class PosedTransaction(val sum: String, val type: String, val category: Category)
