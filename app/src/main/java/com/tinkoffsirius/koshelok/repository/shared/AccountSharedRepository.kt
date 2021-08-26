package com.tinkoffsirius.koshelok.repository.shared

import android.content.SharedPreferences
import com.tinkoffsirius.koshelok.di.AccountShared
import com.tinkoffsirius.koshelok.repository.entities.UserInfo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class AccountSharedRepository @Inject constructor(
    @AccountShared
    private val sharedPreferences: SharedPreferences
) {

    fun getAccount(name: String = ACCOUNT_GOOGLE_ID): Single<String> = Single.fromCallable {
        sharedPreferences.getString(name, "")
    }

    fun saveUserInfo(userInfo: UserInfo): Completable = Completable.fromCallable {
        val data = Json.encodeToString(userInfo)
        sharedPreferences.edit()
            .putString(USER_INFO, data)
            .apply()
    }

    fun getUserInfo(): Single<UserInfo> = Single.fromCallable {
        val data = sharedPreferences.getString(USER_INFO, null)
        if (data != null) {
            Json.decodeFromString(UserInfo.serializer(), data)
        } else {
            UserInfo(null,"","")
        }
    }

    fun saveCurrentWalletId(idWallet: Long) = Completable.fromCallable {
        sharedPreferences.edit()
            .putLong(ID_WALLET, idWallet)
            .apply()
    }

    fun getCurrentWalletId() = Single.fromCallable {
        sharedPreferences.getLong(ID_WALLET, 1)!!
    }

    companion object {
        const val USER_INFO = "user_info"
        const val ACCOUNT_EMAIL = "account_email"
        const val ACCOUNT_GOOGLE_ID = "account_id"
        const val ACCOUNT_ID_TOKEN = "account_id_token"
        const val ACCOUNT_ID_USER = "account_id_user"
        const val ID_WALLET = "id_wallet"
    }
}
