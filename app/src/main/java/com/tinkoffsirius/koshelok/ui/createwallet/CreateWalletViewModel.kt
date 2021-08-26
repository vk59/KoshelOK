package com.tinkoffsirius.koshelok.ui.createwallet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoffsirius.koshelok.config.toCreateWalletData
import com.tinkoffsirius.koshelok.entities.Currency
import com.tinkoffsirius.koshelok.entities.NewWallet
import com.tinkoffsirius.koshelok.repository.CreateWalletRepository
import com.tinkoffsirius.koshelok.repository.entities.CreateWalletData
import com.tinkoffsirius.koshelok.repository.shared.AccountSharedRepository
import com.tinkoffsirius.koshelok.repository.shared.WalletSharedRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.Singles
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

typealias CreateWalletAction = (CreateWalletData, String) -> Single<CreateWalletData>

class CreateWalletViewModel @Inject constructor(
    private val accountSharedRepository: AccountSharedRepository,
    private val walletSharedRepository: WalletSharedRepository,
    private val walletRepository: CreateWalletRepository
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

    fun saveWallet(): LiveData<Unit> {
        val ld = MutableLiveData(Unit)

        disposable += Singles.zip(
            accountSharedRepository.getUserInfo(),
            walletSharedRepository.getWallet()
        )
            .map { (userInfo, walletData) -> walletData.toCreateWalletData(userInfo.id!!) }
            .flatMap { wallet ->
                val transactionAction = getCreateWalletAction(wallet)
                performCreateTransactionAction(transactionAction, wallet)
            }
            .flatMapCompletable { accountSharedRepository.saveCurrentWalletId(it.id!!) }
            .mergeWith(walletSharedRepository.removeWallet())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onComplete = { ld.value = Unit },
                onError = { Timber.e(it) }
            )

        return ld
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
    ) = accountSharedRepository.getAccount(AccountSharedRepository.ACCOUNT_ID_TOKEN)
        .flatMap { accountIdToken ->
            transactionAction(walletData, accountIdToken)
        }

    override fun onCleared() {
        disposable.dispose()
    }
}
