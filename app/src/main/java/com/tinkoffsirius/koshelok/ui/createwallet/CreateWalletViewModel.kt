package com.tinkoffsirius.koshelok.ui.createwallet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoffsirius.koshelok.di.Mocked
import com.tinkoffsirius.koshelok.entities.Currency
import com.tinkoffsirius.koshelok.entities.NewWallet
import com.tinkoffsirius.koshelok.repository.AccountSharedRepository
import com.tinkoffsirius.koshelok.repository.WalletRepository
import com.tinkoffsirius.koshelok.repository.WalletSharedRepository
import com.tinkoffsirius.koshelok.repository.entities.CreateWalletData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.Singles
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

typealias CreateWalletAction = (CreateWalletData, String, String) -> Single<CreateWalletData>

class CreateWalletViewModel @Inject constructor(
    private val accountSharedRepository: AccountSharedRepository,
    private val walletSharedRepository: WalletSharedRepository,
    @Mocked
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

    fun saveWallet() = Completable.fromCallable {
        Singles.zip(
            accountSharedRepository.getUserInfo(),
            walletSharedRepository.getWallet()
        )
            .map { (userInfo, walletData) -> createWalletData(walletData, userInfo.id!!) }
            .flatMap { wallet ->
                val transactionAction = getCreateWalletAction(wallet)
                performCreateTransactionAction(transactionAction, wallet)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { walletData -> saveCurrentWalletId(walletData.id!!) },
                onError = { Timber.e(it) }
            )
    }

    private fun saveCurrentWalletId(id: Long) {
        disposable += accountSharedRepository.saveCurrentWalletId(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onComplete = {},
                onError = Timber::e
            )
    }

    private fun getCreateWalletAction(wallet: CreateWalletData): CreateWalletAction {
        return if (wallet.id == null) {
            walletRepository::createWallet
        } else {
            walletRepository::updateWallet
        }
    }

    private fun performCreateTransactionAction(
        transactionAction: CreateWalletAction,
        walletData: CreateWalletData
    ) = Singles.zip(
        accountSharedRepository.getAccount(AccountSharedRepository.ACCOUNT_GOOGLE_ID),
        accountSharedRepository.getAccount(AccountSharedRepository.ACCOUNT_ID_TOKEN)
    ).flatMap { (accountId, accountIdToken) ->
        transactionAction(walletData, accountId, accountIdToken)
    }

    private fun createWalletData(it: NewWallet, userId: Long) =
        CreateWalletData(
            id = it.id,
            userId = userId,
            name = it.name,
            balance = "0",
            income = "0",
            spending = "0",
            limit = it.limit,
            currencyType = it.currencyType,
            hidden = false
        )

    override fun onCleared() {
        disposable.dispose()
    }
}
