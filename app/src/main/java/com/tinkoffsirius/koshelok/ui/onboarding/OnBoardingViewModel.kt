package com.tinkoffsirius.koshelok.ui.onboarding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.tinkoffsirius.koshelok.repository.AccountSharedRepository
import com.tinkoffsirius.koshelok.repository.WalletRepository
import com.tinkoffsirius.koshelok.repository.entities.UserInfo
import com.tinkoffsirius.koshelok.ui.Event
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class OnBoardingViewModel @Inject constructor(
    private val accountSharedRepository: AccountSharedRepository,
    private val repository: WalletRepository
) : ViewModel() {

    val status: MutableLiveData<Event> = MutableLiveData(Event.Loading())

    private val disposable: CompositeDisposable = CompositeDisposable()

    fun authorize() =
        accountSharedRepository.saveUserInfo(
            UserInfo(2, "1", "hello@gmail.com", "", "")
        )


    fun authorize(account: GoogleSignInAccount) {
        val email = account.email
        val googleId = account.id
        disposable += repository.getUserByEmail(email!!)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    if (it.id == null) {
                        registerUser(email, googleId)
                    } else {
                        saveUser(it)
                    }
                },
                onError = {
                    registerUser(email, googleId)
                }
            )
    }

    private fun registerUser(email: String, googleId: String) {
        disposable += repository.registerUser(
            UserInfo(
                null,
                googleId,
                email,
                "2021-08-24T22:05:22.161Z",
                "2021-08-24T22:05:22.161Z"
            )
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    saveUser(it)
                },
                onError = { status.postValue(Event.Error(it)) }
            )
    }

    private fun saveUser(userInfo: UserInfo) {
        Timber.tag("tut").d(userInfo.toString())
        accountSharedRepository.saveUserInfo(userInfo)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onComplete = {
                    status.postValue(Event.Success())
                },
                onError = {
                    status.postValue(Event.Error(it))
                    Timber.e(it)
                }
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}