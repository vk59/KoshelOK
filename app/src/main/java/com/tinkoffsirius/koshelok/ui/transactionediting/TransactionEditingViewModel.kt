package com.tinkoffsirius.koshelok.ui.transactionediting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoffsirius.koshelok.entities.Category
import com.tinkoffsirius.koshelok.entities.Currency
import com.tinkoffsirius.koshelok.entities.PosedTransaction
import com.tinkoffsirius.koshelok.repository.AccountSharedRepository
import com.tinkoffsirius.koshelok.repository.AccountSharedRepository.Companion.ACCOUNT_ID
import com.tinkoffsirius.koshelok.repository.AccountSharedRepository.Companion.ACCOUNT_ID_TOKEN
import com.tinkoffsirius.koshelok.repository.PosedTransactionSharedRepository
import com.tinkoffsirius.koshelok.repository.WalletRepository
import com.tinkoffsirius.koshelok.repository.entities.CreateTransactionData
import com.tinkoffsirius.koshelok.repository.entities.Response
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import timber.log.Timber

class TransactionEditingViewModel(
    private val transactionSharedRepository: PosedTransactionSharedRepository,
    private val accountSharedRepository: AccountSharedRepository,
    private val walletRepository: WalletRepository
) : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    val transaction: MutableLiveData<PosedTransaction> = MutableLiveData()
    val dateTransaction by lazy { Clock.System.now().toLocalDateTime(TimeZone.UTC) }

    init {
        disposable += transactionSharedRepository.getTransaction()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { transaction.value = it },
                onError = { Timber.e(it) }
            )
    }

    fun removeTransaction() {
        transactionSharedRepository.removeTransaction()
    }

    fun updateTransactionType(type: String): LiveData<Unit> {
        val ld = MutableLiveData<Unit>()

        disposable += transactionSharedRepository.getTransaction()
            .map { it.copy(type = type) }
            .doOnSuccess { transaction.value = it }
            .flatMapCompletable { transactionSharedRepository.saveTransaction(it) }
            .subscribeBy(
                onComplete = { ld.value = Unit },
                onError = Timber::e
            )

        return ld
    }

    fun updateTransactionSum(sum: String): LiveData<Unit> {
        val ld = MutableLiveData<Unit>()

        disposable += transactionSharedRepository.getTransaction()
            .map { it.copy(sum = sum) }
            .doOnSuccess { transaction.value = it }
            .flatMapCompletable { transactionSharedRepository.saveTransaction(it) }
            .subscribeBy(
                onComplete = { ld.value = Unit },
                onError = Timber::e
            )

        return ld
    }

    fun updateTransactionCategory(category: Category): LiveData<Unit> {
        val ld = MutableLiveData<Unit>()

        disposable += transactionSharedRepository.getTransaction()
            .map { it.copy(category = category) }
            .doOnSuccess { transaction.value = it }
            .flatMapCompletable { transactionSharedRepository.saveTransaction(it) }
            .subscribeBy(
                onComplete = { ld.value = Unit },
                onError = Timber::e
            )

        return ld
    }

    override fun onCleared() {
        disposable.dispose()
    }

    fun saveTransaction(): LiveData<Response> {
        val live: MutableLiveData<Response> = MutableLiveData()
        val posedTransaction = transaction.value
        val transactionData = CreateTransactionData(
            null,
            posedTransaction!!.sum,
            posedTransaction.type,
            posedTransaction.category.id!!,
            dateTransaction.toString(),
            Currency.RUB.name
        )
        disposable += walletRepository.createTransaction(
            transactionData,
            accountSharedRepository.getAccount(ACCOUNT_ID),
            accountSharedRepository.getAccount(ACCOUNT_ID_TOKEN)
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { live.value = it },
                onError = { Timber.e(it) }
            )
        return live
    }
}
