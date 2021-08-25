package com.tinkoffsirius.koshelok.ui.transactionediting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoffsirius.koshelok.entities.Category
import com.tinkoffsirius.koshelok.entities.Currency
import com.tinkoffsirius.koshelok.entities.PosedTransaction
import com.tinkoffsirius.koshelok.repository.entities.CreateTransactionData
import com.tinkoffsirius.koshelok.repository.entities.Response
import com.tinkoffsirius.koshelok.repository.main.WalletRepository
import com.tinkoffsirius.koshelok.repository.shared.AccountSharedRepository
import com.tinkoffsirius.koshelok.repository.shared.AccountSharedRepository.Companion.ACCOUNT_GOOGLE_ID
import com.tinkoffsirius.koshelok.repository.shared.PosedTransactionSharedRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.Singles
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import timber.log.Timber
import javax.inject.Inject

typealias CreateTransactionAction = (CreateTransactionData, Long, String) -> Single<Response>

class TransactionEditingViewModel @Inject constructor(
    private val transactionSharedRepository: PosedTransactionSharedRepository,
    private val accountSharedRepository: AccountSharedRepository,
    private val walletRepository: WalletRepository
) : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    val transaction: MutableLiveData<PosedTransaction> = MutableLiveData()

    private var defaultDataTime: LocalDateTime =
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    val transactionDateTime: MutableLiveData<LocalDateTime> = MutableLiveData(defaultDataTime)

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

    fun updateDate(newDate: LocalDateTime) {

        this.defaultDataTime = newDate
        transactionDateTime.value = newDate
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

    fun saveTransaction(): LiveData<Response> {
        val liveData: MutableLiveData<Response> = MutableLiveData()
        disposable += transactionSharedRepository
            .getTransaction()
            .map(::createTransactionData)
            .flatMap { posedTransaction ->
                val transactionAction = getCreateTransactionAction(posedTransaction)
                performCreateTransactionAction(transactionAction, posedTransaction)
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
        transactionAction: CreateTransactionAction,
        posedTransaction: CreateTransactionData
    ) = Singles.zip(
        accountSharedRepository.getAccount(ACCOUNT_GOOGLE_ID),
        accountSharedRepository.getAccount(ACCOUNT_GOOGLE_ID)
    ).flatMap { (accountId, accountIdToken) ->
        transactionAction(posedTransaction, accountId.toLong(), accountIdToken)
    }

    private fun getCreateTransactionAction(posedTransaction: CreateTransactionData): CreateTransactionAction {
        return if (posedTransaction.id == null) {
            walletRepository::createTransaction
        } else {
            walletRepository::updateTransaction
        }
    }

    private fun createTransactionData(it: PosedTransaction) =
        CreateTransactionData(
            it.id,
            it.sum,
            it.type,
            it.category.id!!,
            defaultDataTime.toString(),
            Currency.RUB.name
        )

    override fun onCleared() {
        disposable.dispose()
    }
}
