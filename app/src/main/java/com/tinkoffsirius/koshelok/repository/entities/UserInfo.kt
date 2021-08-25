package com.tinkoffsirius.koshelok.repository.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    @SerialName("id")
    val id: Long?,
    @SerialName("googleToken")
    val googleToken: String,
    @SerialName("email")
    val email: String,
    @SerialName("lastEntrance")
    val lastEntrance: String,
    @SerialName("registrationDate")
    val registrationDate: String
)
