package com.tinkoff_sirius.koshelok.entitis

import kotlinx.serialization.Serializable

@Serializable
data class PosedTransaction(var sum: String, var type: String, var category: String)
