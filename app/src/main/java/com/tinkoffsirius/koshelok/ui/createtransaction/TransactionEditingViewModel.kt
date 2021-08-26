package com.tinkoffsirius.koshelok.ui.createtransaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoffsirius.koshelok.config.toCategory
import com.tinkoffsirius.koshelok.config.toCreateTransactionData
import com.tinkoffsirius.koshelok.entities.Category
import com.tinkoffsirius.koshelok.entities.PosedTransaction
import com.tinkoffsirius.koshelok.entities.TransactionType
import com.tinkoffsirius.koshelok.repository.TransactionEditingRepository
import com.tinkoffsirius.koshelok.repository.entities.CreateTransactionData
import com.tinkoffsirius.koshelok.repository.shared.AccountSharedRepository
import com.tinkoffsirius.koshelok.repository.shared.PosedTransactionSharedRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
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

typealias CreateTransactionAction = (CreateTransactionData) -> Completable

class TransactionEditingViewModel @Inject constructor(
    private val transactionSharedRepository: PosedTransactionSharedRepository,
    private val accountSharedRepository: AccountSharedRepository,
    private val transactionEditingRepository: TransactionEditingRepository
) : ViewModel() {

    val transaction: MutableLiveData<PosedTransaction> = MutableLiveData()

    val categories = MutableLiveData<List<Category>>()

    private var defaultDataTime: LocalDateTime =
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    var transactionDateTime: LocalDateTime = defaultDataTime

    private val disposable: CompositeDisposable = CompositeDisposable()

    init {
        disposable += transactionSharedRepository.getTransaction()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { transaction.value = it },
                onError = { Timber.e(it) }
            )
    }

    fun saveTransaction(): LiveData<Unit> {
        val liveData = MutableLiveData<Unit>()
        disposable += Singles.zip(
            getWalletId(), transactionSharedRepository.getTransaction()
        )
            .map { (walletId, posedTransaction) ->
                val dateFormat = "$defaultDataTime:00"
                posedTransaction.toCreateTransactionData(
                    walletId,
                    dateFormat
                )
            }
            .flatMapCompletable { createTransactionData ->
                performCreateTransactionAction(
                    getCreateTransactionAction(createTransactionData),
                    createTransactionData
                )
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onComplete = { liveData.value = Unit },
                onError = { Timber.e(it) }
            )
        return liveData
    }

    fun removeTransaction() {
        transactionSharedRepository.removeTransaction()
    }

    fun updateDate(newDate: LocalDateTime) {
        this.defaultDataTime = newDate
        transactionDateTime = newDate
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

    private fun getWalletId(): Single<Long> {
        return accountSharedRepository.getCurrentWalletId()
    }

    fun getCategories(type: TransactionType) {
        val getCategoriesAction = when (type) {
            TransactionType.INCOME -> transactionEditingRepository::getIncomeCategories
            TransactionType.OUTCOME -> transactionEditingRepository::getOutcomeCategories
        }
        accountSharedRepository.getUserInfo()
            .flatMap { userInfo -> getCategoriesAction(userInfo.id!!) }
            .map { categoryData -> categoryData.map { it.toCategory() } }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { categories.postValue(it) },
                onError = Timber::e
            )
    }

    private fun performCreateTransactionAction(
        transactionAction: CreateTransactionAction,
        posedTransaction: CreateTransactionData
    ) = transactionAction(posedTransaction)

    private fun getCreateTransactionAction(createTransactionData: CreateTransactionData): CreateTransactionAction {
        return if (createTransactionData.id == null) {
            transactionEditingRepository::createTransaction
        } else {
            transactionEditingRepository::updateTransaction
        }
    }

    override fun onCleared() {
        disposable.dispose()
    }
}
