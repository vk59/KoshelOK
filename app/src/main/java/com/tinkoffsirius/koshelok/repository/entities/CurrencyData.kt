package com.tinkoffsirius.koshelok.repository.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyData(
    @SerialName("usd")
    val usd: String,
    @SerialName("eur")
    val eur: String,
    @SerialName("gbp")
    val gbp: String,
    @SerialName("usdUp")
    val usdUp: Boolean,
    @SerialName("eurUp")
    val eurUp: Boolean,
    @SerialName("gbpUp")
    val gbpUp: Boolean
)
