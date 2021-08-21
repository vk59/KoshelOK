package com.tinkoff_sirius.koshelok.entitis

import kotlinx.serialization.Serializable

@Serializable
data class PosedTransaction(val sum: String, val type: String, val category: Category)
