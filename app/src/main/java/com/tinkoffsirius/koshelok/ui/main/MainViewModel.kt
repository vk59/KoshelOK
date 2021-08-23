package com.tinkoffsirius.koshelok.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.tinkoffsirius.koshelok.entities.Category
import com.tinkoffsirius.koshelok.entities.PosedTransaction
import com.tinkoffsirius.koshelok.entities.TransactionType
import com.tinkoffsirius.koshelok.repository.AccountSharedRepository
import com.tinkoffsirius.koshelok.repository.AccountSharedRepository.Companion.ACCOUNT_ID
import com.tinkoffsirius.koshelok.repository.AccountSharedRepository.Companion.ACCOUNT_ID_TOKEN
import com.tinkoffsirius.koshelok.repository.PosedTransactionSharedRepository
import com.tinkoffsirius.koshelok.repository.WalletRepository
import com.tinkoffsirius.koshelok.repository.entities.TransactionData
import com.tinkoffsirius.koshelok.repository.entities.WalletData
import com.tinkoffsirius.koshelok.ui.main.adapters.model.MainItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.datetime.*
import timber.log.Timber
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

class MainViewModel(
    private val accountSharedRepository: AccountSharedRepository,
    private val transactionRepository: PosedTransactionSharedRepository,
    private val walletRepository: WalletRepository
) : ViewModel() {

    val items: MutableLiveData<List<MainItem>> = MutableLiveData(listOf())

    val isThereTransactions = MutableLiveData(true)

    private var lastTimeBackPressed: Instant = Instant.DISTANT_PAST
    private val disposable: CompositeDisposable = CompositeDisposable()

    init {
        disposable += updateTransactions()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(onError = Timber::e)
    }

    private fun updateTransactions(): Completable {
        return walletRepository.getWalletById(
            1,
            "", ""
        )
            .doOnSuccess { walletData ->
                isThereTransactions.postValue(walletData.transactions.isNotEmpty())
                items.postValue(createNewMainItemList(walletData))
            }.ignoreElement()
    }

    fun deleteTransactionById(id: Long) {
        disposable += walletRepository.deleteTransactionById(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .flatMapCompletable { updateTransactions() }
            .subscribeBy(
                onError = { Timber.e(it) }
            )
    }

    @OptIn(ExperimentalTime::class)
    fun onBackPressed(): LiveData<Boolean> = liveData {
        val now = Clock.System.now()
        if (now.minus(lastTimeBackPressed) < Duration.seconds(3)) {
            emit(true)
        } else {
            lastTimeBackPressed = now
            emit(false)
        }
    }

    fun editCurrentTransaction(element: MainItem.Transaction) {
        transactionRepository.removeTransaction()
        transactionRepository.saveTransaction(
            PosedTransaction(
                element.sum,
                element.category.typeName.name,
                element.category,
                element.id
            )
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onComplete = { Timber.d("Saved") },
                onError = { Timber.e(it) }
            )
    }

    private fun createNewMainItemList(walletData: WalletData): List<MainItem> {
        val items: MutableList<MainItem> = mutableListOf(
            MainItem.Header(
                walletData.name,
                "${walletData.balance} ${walletData.currencyType}",
                "${walletData.income} ${walletData.currencyType}",
                "${walletData.spending} ${walletData.currencyType}",
                walletData.limit
            )
        )
        val transactions = walletData.transactions.toTransactionItems()

        val transItems = mutableListOf<MainItem>()
        val transDate = transactions.groupBy { it.date }
        transDate.forEach { (date, list) ->
            transItems.add(MainItem.Date(date))
            transItems.addAll(list)
        }
        items.addAll(transItems)
        return items
    }

    private fun List<TransactionData>.toTransactionItems(): List<MainItem.Transaction> {
        return this.map {
            val transactionEnum =
                if (it.transactionType == "INCOME") {
                    TransactionType.INCOME
                } else {
                    TransactionType.OUTCOME
                }
            MainItem.Transaction(
                it.id!!,
                it.amount,
                it.currency,
                Category(
                    it.category.id,
                    transactionEnum,
                    it.category.name,
                    it.category.icon,
                    it.category.color
                ),
                it.date.toLocalDateTime().toLocalDate(),
                it.date.toLocalDateTime().toStringTime()
            )
        }
    }

    private fun LocalDateTime.toLocalDate(): LocalDate {
        return LocalDate(this.year, this.monthNumber, this.dayOfMonth)
    }

    private fun LocalDateTime.toStringTime(): String {
        return if (this.minute > 10) "${this.hour}:${this.minute}" else "${this.hour}:0${this.minute}"
    }

    override fun onCleared() {
        disposable.dispose()
    }
}
