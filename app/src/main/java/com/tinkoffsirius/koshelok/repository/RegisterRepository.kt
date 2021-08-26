package com.tinkoffsirius.koshelok.repository

import com.tinkoffsirius.koshelok.repository.entities.UserInfo
import io.reactivex.rxjava3.core.Single

interface RegisterRepository {

    fun registerUser(userInfo: UserInfo): Single<UserInfo>

    fun getUserByEmail(email: String): Single<UserInfo>
}
