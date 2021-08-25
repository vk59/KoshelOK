package com.tinkoffsirius.koshelok.ui.walletlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoffsirius.koshelok.entities.NewWallet
import com.tinkoffsirius.koshelok.repository.WalletListRepository
import com.tinkoffsirius.koshelok.repository.entities.UserInfo
import com.tinkoffsirius.koshelok.repository.entities.WalletDataItem
import com.tinkoffsirius.koshelok.repository.shared.AccountSharedRepository
import com.tinkoffsirius.koshelok.repository.shared.WalletSharedRepository
import com.tinkoffsirius.koshelok.ui.Event
import com.tinkoffsirius.koshelok.ui.walletlist.adapters.WalletItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class WalletListViewModel @Inject constructor(
    private val accountSharedRepository: AccountSharedRepository,
    private val walletRepository: WalletListRepository,
    private val walletSharedRepository: WalletSharedRepository
) : ViewModel() {

    val items: MutableLiveData<List<WalletItem>> = MutableLiveData(listOf())

    val userInfoBalance = MutableLiveData(UserInfoBalance("0", "0", "0"))

    val isThereWallets = MutableLiveData<Boolean>()

    val status: MutableLiveData<Event> = MutableLiveData(Event.Success())

    private var userId: Long? = 0L

    private val disposable: CompositeDisposable = CompositeDisposable()

    private var currentUserInfo = UserInfo(0, "","","","")

    init {
        initUserAccountData()
    }

    fun updateUserInfo() {
        updateUserInfo(currentUserInfo.id!!, currentUserInfo.googleToken)
    }

    fun deleteWallet(walletItem: WalletItem) {
        disposable += walletRepository.deleteWalletById(walletItem.id!!)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onComplete = { Timber.tag("tut").d("Successfully deleted") },
                onError = Timber::e
            )
        status.value = Event.Loading()
        updateUserInfo()
    }

    fun editWallet(item: WalletItem) {
        walletSharedRepository.saveWallet(
            with(item) {
                NewWallet(id, name, limit, currencyType)
            }
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onComplete = {},
                onError = Timber::e
            )
    }

    fun showWallet(walletItem: WalletItem): Completable {
        return accountSharedRepository.saveCurrentWalletId(walletItem.id!!)
    }

    private fun createNewWalletItemList(walletsList: List<WalletDataItem>): List<WalletItem> {
        return walletsList.map { WalletItem(it.id, it.name, it.balance, it.limit, it.currencyType) }
    }

    private fun updateUserInfo(userId: Long, token: String): Disposable {
        status.postValue(Event.Loading())
        return walletRepository.getUserInfoWallets(idUser = userId, idToken = token)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { userInfoWallets ->
                    status.postValue(Event.Success())
                    isThereWallets.postValue(userInfoWallets.wallets.isNotEmpty())
                    Timber.tag("tut").d(userInfoWallets.wallets.toString())
                    Timber.tag("tut").d(createNewWalletItemList(userInfoWallets.wallets).toString())
                    items.postValue(createNewWalletItemList(userInfoWallets.wallets))
                    userInfoBalance.postValue(
                        UserInfoBalance(
                            "${userInfoWallets.overallBalance} RUB",
                            "${userInfoWallets.overallIncome} RUB",
                            "${userInfoWallets.overallSpending} RUB"
                        )
                    )
                },
                onError = {
                    status.postValue(Event.Error(it))
                }
            )
    }

    private fun initUserAccountData() {
        accountSharedRepository.getUserInfo()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy {
                currentUserInfo = it
                disposable += updateUserInfo(it.id!!, it.googleToken)
            }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}

data class UserInfoBalance(
    val overallBalance: String,
    val overallIncome: String,
    val overallOutcome: String
)
