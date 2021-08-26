package com.tinkoffsirius.koshelok.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.tinkoffsirius.koshelok.repository.RegisterRepository
import com.tinkoffsirius.koshelok.repository.entities.UserInfo
import com.tinkoffsirius.koshelok.repository.shared.AccountSharedRepository
import com.tinkoffsirius.koshelok.utils.Event
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class OnBoardingViewModel @Inject constructor(
    private val accountSharedRepository: AccountSharedRepository,
    private val repository: RegisterRepository
) : ViewModel() {

    val status: MutableLiveData<Event> = MutableLiveData()

    private val disposable: CompositeDisposable = CompositeDisposable()

    fun authorize(account: GoogleSignInAccount): LiveData<Unit> {
        val ld = MutableLiveData<Unit>()
        status.postValue(Event.Loading())
        val email = account.email
        val googleId = account.id
        disposable += repository.getUserByEmail(email!!)
            .flatMapCompletable { registerOrSaveUser(it, email, googleId!!) }
            .onErrorResumeNext { registerOrSaveUser(null, email, googleId!!) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onComplete = {
                    status.value = Event.Success()
                    ld.postValue(Unit)
                },
                onError = { status.value = Event.Error(it) }
            )
        return ld
    }

    private fun registerOrSaveUser(
        it: UserInfo?,
        email: String,
        googleId: String
    ): Completable {
        return if (it == null) { registerUser(email, googleId) } else { saveUser(it) }
    }

    private fun registerUser(email: String, googleId: String): Completable {
        return repository.registerUser(
            UserInfo(
                null,
                googleId,
                email
            )
        ).flatMapCompletable { saveUser(it) }
    }

    private fun saveUser(userInfo: UserInfo): Completable {
        return accountSharedRepository.saveUserInfo(userInfo)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}
