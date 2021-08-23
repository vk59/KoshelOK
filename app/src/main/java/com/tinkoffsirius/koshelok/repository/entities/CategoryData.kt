package com.tinkoffsirius.koshelok.repository.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryData(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("color")
    val color: Int,
    @SerialName("icon")
    val icon: Int,
)
