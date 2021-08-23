package com.tinkoffsirius.koshelok.ui.createwallet.setname

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoffsirius.koshelok.entities.Currency
import com.tinkoffsirius.koshelok.entities.NewWallet
import com.tinkoffsirius.koshelok.repository.AccountSharedRepository
import com.tinkoffsirius.koshelok.repository.WalletRepository
import com.tinkoffsirius.koshelok.repository.WalletSharedRepository
import com.tinkoffsirius.koshelok.repository.entities.Response
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

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

        disposable +=walletSharedRepository.getWallet()
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

        disposable +=walletSharedRepository.getWallet()
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
        // TODO()
        /*
        val posedTransaction = transaction.value!!
        val transactionData = CreateTransactionData(
            posedTransaction.id,
            posedTransaction.sum,
            posedTransaction.type,
            posedTransaction.category.id!!,
            dateTransaction.toString(),
            Currency.RUB.name
        )
        if (posedTransaction.id == null) {
            disposable += walletRepository.createTransaction(
                transactionData,
                accountSharedRepository.getAccount(ACCOUNT_ID),
                accountSharedRepository.getAccount(ACCOUNT_ID_TOKEN)
            ).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onSuccess = { liveData.value = it },
                    onError = { Timber.e(it) }
                )
        } else {
            disposable += walletRepository.updateTransaction(
                transactionData,
                accountSharedRepository.getAccount(ACCOUNT_ID),
                accountSharedRepository.getAccount(ACCOUNT_ID_TOKEN)
            ).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onSuccess = { liveData.value = it },
                    onError = { Timber.e(it) }
                )
        }
         */

        return liveData
    }

    override fun onCleared() {
        disposable.dispose()
    }
}
