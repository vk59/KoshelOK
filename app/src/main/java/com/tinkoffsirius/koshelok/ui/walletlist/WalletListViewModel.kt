package com.tinkoffsirius.koshelok.ui.walletlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoffsirius.koshelok.repository.AccountSharedRepository
import com.tinkoffsirius.koshelok.repository.WalletRepository
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
    private val walletRepository: WalletRepository
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

    private fun createNewWalletItemList(walletsList: List<WalletDataItem>): List<WalletItem> {
        return walletsList.map { WalletItem(it.id, it.name, it.balance, it.currencyType) }
    }
}

data class UserInfoBalance(
    val overallBalance: String,
    val overallIncome: String,
    val overallOutcome: String
)
