package com.tinkoffsirius.koshelok.ui.createwallet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoffsirius.koshelok.entities.Currency
import com.tinkoffsirius.koshelok.entities.NewWallet
import com.tinkoffsirius.koshelok.repository.AccountSharedRepository
import com.tinkoffsirius.koshelok.repository.WalletRepository
import com.tinkoffsirius.koshelok.repository.WalletSharedRepository
import com.tinkoffsirius.koshelok.repository.entities.Response
import com.tinkoffsirius.koshelok.repository.entities.WalletData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.Singles
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

typealias CreateWalletAction = (WalletData, String, String) -> Single<Response>

class CreateWalletViewModel(
    private val accountSharedRepository: AccountSharedRepository,
    private val walletSharedRepository: WalletSharedRepository,
    private val walletRepository: WalletRepository
) : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()
    val wallet: MutableLiveData<NewWallet> = MutableLiveData()

    init {
        disposable += walletSharedRepository.getWallet()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { wallet.value = it },
                onError = { Timber.e(it) }
            )
    }

    fun updateName(name: String): LiveData<Unit> {
        val ld = MutableLiveData<Unit>()

        disposable += walletSharedRepository.getWallet()
            .map { it.copy(name = name) }
            .doOnSuccess { wallet.value = it }
            .flatMapCompletable { walletSharedRepository.saveWallet(it) }
            .subscribeBy(
                onComplete = { ld.value = Unit },
                onError = Timber::e
            )

        return ld
    }

    fun updateCurrency(currency: Currency): LiveData<Unit> {
        val ld = MutableLiveData<Unit>()

        disposable += walletSharedRepository.getWallet()
            .map { it.copy(currencyType = currency.name) }
            .doOnSuccess { wallet.value = it }
            .flatMapCompletable { walletSharedRepository.saveWallet(it) }
            .subscribeBy(
                onComplete = { ld.value = Unit },
                onError = Timber::e
            )

        return ld
    }

    fun updateLimit(limit: String): LiveData<Unit> {
        val ld = MutableLiveData<Unit>()

        disposable += walletSharedRepository.getWallet()
            .map { it.copy(limit = limit) }
            .doOnSuccess { wallet.value = it }
            .flatMapCompletable { walletSharedRepository.saveWallet(it) }
            .subscribeBy(
                onComplete = { ld.value = Unit },
                onError = Timber::e
            )

        return ld
    }



    fun createWallet(): LiveData<Response> {
        val liveData: MutableLiveData<Response> = MutableLiveData()
        disposable += walletSharedRepository
            .getWallet()
            .map(::createWalletData)
            .flatMap { wallet ->
                performCreateTransactionAction(walletRepository::createWallet, wallet)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { liveData.value = it },
                onError = { Timber.e(it) }
            )
        return liveData
    }

    private fun performCreateTransactionAction(
        transactionAction: CreateWalletAction,
        walletData: WalletData
    ) = Singles.zip(
        accountSharedRepository.getAccount(AccountSharedRepository.ACCOUNT_ID),
        accountSharedRepository.getAccount(AccountSharedRepository.ACCOUNT_ID_TOKEN)
    ).flatMap { (accountId, accountIdToken) ->
        transactionAction(walletData, accountId, accountIdToken)
    }

    private fun createWalletData(it: NewWallet) =
        WalletData(
            null,
            it.name,
            "0.00",
            "0.00",
            "0.00",
            it.limit,
            it.currencyType,
            listOf()
        )

    override fun onCleared() {
        disposable.dispose()
    }
}
