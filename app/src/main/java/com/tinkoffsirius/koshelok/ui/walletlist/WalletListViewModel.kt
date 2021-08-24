package com.tinkoffsirius.koshelok.ui.walletlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoffsirius.koshelok.entities.NewWallet
import com.tinkoffsirius.koshelok.repository.AccountSharedRepository
import com.tinkoffsirius.koshelok.repository.WalletRepository
import com.tinkoffsirius.koshelok.repository.WalletSharedRepository
import com.tinkoffsirius.koshelok.repository.entities.WalletDataItem
import com.tinkoffsirius.koshelok.ui.Event
import com.tinkoffsirius.koshelok.ui.walletlist.adapters.WalletItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class WalletListViewModel(
    private val accountSharedRepository: AccountSharedRepository,
    private val walletRepository: WalletRepository,
    private val walletSharedRepository: WalletSharedRepository
) : ViewModel() {

    val items: MutableLiveData<List<WalletItem>> = MutableLiveData(listOf())

    val userInfoBalance = MutableLiveData(UserInfoBalance("0", "0", "0"))

    val isThereWallets = MutableLiveData<Boolean>()

    val status: MutableLiveData<Event> = MutableLiveData(Event.Success())

    private var userId = 0L

    private val disposable: CompositeDisposable = CompositeDisposable()

    init {
        disposable += updateUserInfo()
        initUserAccountData()
    }

    fun updateUserInfo(): Disposable {
        status.postValue(Event.Loading())
        return walletRepository.getUserInfoWallets(1, "")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { userInfoWallets ->
                    status.postValue(Event.Success())
                    isThereWallets.postValue(userInfoWallets.wallets.isNotEmpty())
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

    fun deleteWallet(walletItem: WalletItem) {
        disposable += walletRepository.deleteWalletById(walletItem.id!!, "", "")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { Timber.d(it.message) },
                onError = Timber::e
            )
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

    private fun createNewWalletItemList(walletsList: List<WalletDataItem>): List<WalletItem> {
        return walletsList.map { WalletItem(it.id, it.name, it.balance, it.limit, it.currencyType) }
    }

    private fun initUserAccountData() {
        accountSharedRepository.getAccountId()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy { userId = it.toLong() }
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
