package com.tinkoff_sirius.koshelok.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoff_sirius.koshelok.config.AppConfig
import com.tinkoff_sirius.koshelok.ui.main.adapters.model.MainItem
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

class MainViewModel : ViewModel() {
    var items: MutableLiveData<MutableList<MainItem>> = MutableLiveData(mutableListOf())

    val exitFlag = MutableLiveData(false)

    // Transactions -> MainItem
    private var transactionsItems = listOf<MainItem>()
    // transactions из repository Single<LIST>
    private var transactions = AppConfig.mainTransactionsExample.toMutableList()

    init {
        createNewMainItemList()
    }

    private fun createNewMainItemList(/* Single<LIST> */) {
        val transItems = mutableListOf<MainItem>()
        val transDate = transactions.groupBy { it.date }
        transDate.forEach { (date, list) ->
            transItems.add(MainItem.Date(date))
            transItems.addAll(list)
        }

        transactionsItems = transItems.toList()

        val header = AppConfig.headerExample.toMutableList()
        header.addAll(transactionsItems)
        items.value = header
    }

    fun loadData() {
        // TODO: Реализовать через обращение к серверу
        createNewMainItemList()
    }

    fun deleteTransaction(element: MainItem) {
        // TODO: Реализовать через обращение к серверу
        transactions.remove(element)
        createNewMainItemList()
    }

    private var lastTimeBackPressed: Instant = Instant.DISTANT_PAST
    @OptIn(ExperimentalTime::class)
    fun onBackPressed() {
        val now = Clock.System.now()
        if (now.minus(lastTimeBackPressed) < Duration.seconds(3)) {
            exitFlag.value = true
        } else {
            lastTimeBackPressed = now
        }
    }
}
