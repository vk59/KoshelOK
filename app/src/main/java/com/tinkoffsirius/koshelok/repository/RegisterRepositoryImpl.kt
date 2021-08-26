package com.tinkoffsirius.koshelok.repository

import com.tinkoffsirius.koshelok.repository.entities.UserInfo
import com.tinkoffsirius.koshelok.service.WalletService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor() : RegisterRepository {

    @Inject
    lateinit var walletService: WalletService

    override fun registerUser(userInfo: UserInfo): Single<UserInfo> {
        return walletService.registerUser(userInfo)
    }

    override fun getUserByEmail(email: String): Single<UserInfo> {
        return walletService.getUserByEmail(email)
    }
}