package com.tinkoffsirius.koshelok.ui.walletlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoffsirius.koshelok.entities.NewWallet
import com.tinkoffsirius.koshelok.repository.AccountSharedRepository
import com.tinkoffsirius.koshelok.repository.WalletRepository
import com.tinkoffsirius.koshelok.repository.WalletSharedRepository
import com.tinkoffsirius.koshelok.repository.entities.WalletDataItem
import com.tinkoffsirius.koshelok.ui.walletlist.adapters.WalletItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
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

    val userInfoBalance = MutableLiveData(UserInfoBalance("0.00", "0.00", "0.00"))

    val isThereWallets = MutableLiveData<Boolean>()

    private val disposable: CompositeDisposable = CompositeDisposable()

    init {
        disposable += updateUserInfo()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(onError = Timber::e)
    }

    fun updateUserInfo(): Completable {
        return walletRepository.getUserInfoWallets("", "")
        .doOnSuccess { userInfoWallets ->
            isThereWallets.postValue(userInfoWallets.wallets.isNotEmpty())
            items.postValue(createNewWalletItemList(userInfoWallets.wallets))
            userInfoBalance.postValue(
                UserInfoBalance(
                "${userInfoWallets.overallBalance} RUB",
                    "${userInfoWallets.overallIncome} RUB",
                    "${userInfoWallets.overallSpending} RUB"
                )
            )
        }.ignoreElement()
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
