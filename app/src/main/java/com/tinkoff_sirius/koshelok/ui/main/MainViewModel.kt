package com.tinkoff_sirius.koshelok.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoff_sirius.koshelok.entities.Category
import com.tinkoff_sirius.koshelok.entities.TransactionType
import com.tinkoff_sirius.koshelok.repository.AccountSharedRepository
import com.tinkoff_sirius.koshelok.repository.AccountSharedRepository.Companion.ACCOUNT_ID
import com.tinkoff_sirius.koshelok.repository.AccountSharedRepository.Companion.ACCOUNT_ID_TOKEN
import com.tinkoff_sirius.koshelok.repository.Repository
import com.tinkoff_sirius.koshelok.repository.entities.TransactionData
import com.tinkoff_sirius.koshelok.repository.entities.WalletData
import com.tinkoff_sirius.koshelok.ui.main.adapters.model.MainItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.datetime.*
import timber.log.Timber
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

class MainViewModel(private val accountSharedRepository: AccountSharedRepository) : ViewModel() {
    val items: MutableLiveData<MutableList<MainItem>> = MutableLiveData(mutableListOf())
    val exitFlag = MutableLiveData(false)
    private var lastTimeBackPressed: Instant = Instant.DISTANT_PAST
    private val repository by lazy {
        Repository()
    }

    private fun createNewMainItemList(walletData: WalletData) {
        val header: MutableList<MainItem> = mutableListOf(
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
        header.addAll(transItems)
        items.value = header
    }

    fun loadData() {
        repository.getWalletById(
            1,
            accountSharedRepository.getAccount(ACCOUNT_ID),
            accountSharedRepository.getAccount(ACCOUNT_ID_TOKEN)
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    createNewMainItemList(it)
                },
                onError = {
                    Timber.d("error ${it}")
                }
            )
    }

    fun deleteTransactionById(id: Long) {
        repository.deleteTransactionById(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    Log.d("DELETE", it.message)
                    loadData()
                },
                onError = { Log.d("DELETE", "error") }
            )
    }

    @OptIn(ExperimentalTime::class)
    fun onBackPressed() {
        val now = Clock.System.now()
        if (now.minus(lastTimeBackPressed) < Duration.seconds(3)) {
            exitFlag.value = true
        } else {
            lastTimeBackPressed = now
        }
    }

    private fun List<TransactionData>.toTransactionItems(): List<MainItem.Transaction> {
        return this.map {
            val transactionEnum =
                if (it.transactionType == "INCOME")
                    TransactionType.INCOME
                else
                    TransactionType.OUTCOME

            MainItem.Transaction(
                it.id,
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
}

