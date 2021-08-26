package com.tinkoffsirius.koshelok.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoffsirius.koshelok.config.toMainItemTransaction
import com.tinkoffsirius.koshelok.entities.PosedTransaction
import com.tinkoffsirius.koshelok.repository.MainRepository
import com.tinkoffsirius.koshelok.repository.entities.UserInfo
import com.tinkoffsirius.koshelok.repository.entities.WalletData
import com.tinkoffsirius.koshelok.repository.shared.AccountSharedRepository
import com.tinkoffsirius.koshelok.repository.shared.PosedTransactionSharedRepository
import com.tinkoffsirius.koshelok.ui.main.adapters.model.MainItem
import com.tinkoffsirius.koshelok.utils.Event
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.Singles
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val accountSharedRepository: AccountSharedRepository,
    private val transactionRepository: PosedTransactionSharedRepository,
    private val mainRepository: MainRepository
) : ViewModel() {

    val items: MutableLiveData<List<MainItem>> = MutableLiveData(listOf())

    val isThereTransactions = MutableLiveData(true)

    val status: MutableLiveData<Event> = MutableLiveData(Event.Loading())

    private val disposable: CompositeDisposable = CompositeDisposable()

    init {
        status.postValue(Event.Loading())
        disposable += loadData()
    }

    private fun loadData(): Disposable {
        return getUserInfo()
            .flatMap { (userInfo, walletId) -> updateTransactions(userInfo, walletId) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { walletData ->
                    status.postValue(Event.Success())
                    isThereTransactions.postValue(walletData.transactions.isNotEmpty())
                    items.postValue(createNewMainItemList(walletData))
                },
                onError = {
                    status.postValue(Event.Error(it))
                    Timber.e(it)
                }
            )
    }

    private fun getUserInfo() = Singles.zip(
        accountSharedRepository.getUserInfo(),
        accountSharedRepository.getCurrentWalletId()
    )

    fun updateTransactions() {
        disposable += loadData()
    }

    private fun updateTransactions(userInfo: UserInfo, walletId: Long): Single<WalletData> {
        return mainRepository.getWalletById(walletId, userInfo.id!!, userInfo.googleToken)
    }

    fun deleteTransactionById(id: Long) {
        status.value = Event.Loading()

        disposable += mainRepository.deleteTransactionById(id)
            .andThen(
                getUserInfo().flatMap { (userInfo, walletId) ->
                    updateTransactions(userInfo, walletId)
                }
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    status.value = Event.Success()
                    items.postValue(createNewMainItemList(it))
                },
                onError = {
                    status.postValue(Event.Error(it))
                    Timber.e(it)
                }
            )
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
                onError = {
                    status.postValue(Event.Error(it))
                    Timber.e(it)
                }
            )
    }

    private fun createNewMainItemList(walletData: WalletData): List<MainItem> {
        val items: MutableList<MainItem> = createHeaderInList(walletData)
        val transactions = walletData.transactions.map { it.toMainItemTransaction() }

        val transItems = mutableListOf<MainItem>()
        val transDate = transactions.groupBy { it.date }
        transDate.forEach { (date, list) ->
            transItems.add(MainItem.Date(date))
            transItems.addAll(list)
        }
        items.addAll(transItems)
        return items
    }

    private fun createHeaderInList(walletData: WalletData): MutableList<MainItem> =
        mutableListOf(
            MainItem.Header(
                walletData.name,
                "${walletData.balance} ${walletData.currencyType}",
                "${walletData.income} ${walletData.currencyType}",
                "${walletData.spending} ${walletData.currencyType}",
                walletData.limit
            )
        )

    override fun onCleared() {
        disposable.dispose()
    }
}
