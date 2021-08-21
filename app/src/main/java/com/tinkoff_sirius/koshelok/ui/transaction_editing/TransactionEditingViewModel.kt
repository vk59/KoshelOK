package com.tinkoff_sirius.koshelok.ui.transaction_editing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoff_sirius.koshelok.entities.Category
import com.tinkoff_sirius.koshelok.entities.PosedTransaction
import com.tinkoff_sirius.koshelok.repository.PosedTransactionSharedRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class TransactionEditingViewModel(private val transactionSharedRepository: PosedTransactionSharedRepository) : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    val transaction: MutableLiveData<PosedTransaction> = MutableLiveData()

    init {
        disposable += transactionSharedRepository.getTransaction()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy (
                onSuccess = {transaction.value = it} ,
                onError = { Timber.e(it)}
            )
    }

    fun removeTransaction(){
        transactionSharedRepository.removeTransaction()
    }

    fun updateTransactionType(type: String): LiveData<Unit> {
        val ld = MutableLiveData<Unit>()

        disposable += transactionSharedRepository.getTransaction()
            .map { it.copy(type = type) }
            .doOnSuccess { transaction.value = it }
            .flatMapCompletable { transactionSharedRepository.saveTransaction(it) }
            .subscribeBy(
                onComplete =  { ld.value = Unit },
                onError =  Timber::e
            )

        return ld
    }

    fun updateTransactionSum(sum: String): LiveData<Unit>{
        val ld = MutableLiveData<Unit>()

        disposable += transactionSharedRepository.getTransaction()
            .map { it.copy(sum = sum) }
            .doOnSuccess { transaction.value = it }
            .flatMapCompletable { transactionSharedRepository.saveTransaction(it) }
            .subscribeBy(
                onComplete =  { ld.value = Unit },
                onError =  Timber::e
            )

        return ld
    }

    fun updateTransactionCategory(category: Category): LiveData<Unit>{
        val ld = MutableLiveData<Unit>()

        disposable += transactionSharedRepository.getTransaction()
            .map { it.copy(category = category) }
            .doOnSuccess { transaction.value = it }
            .flatMapCompletable { transactionSharedRepository.saveTransaction(it) }
            .subscribeBy(
                onComplete =  { ld.value = Unit },
                onError =  Timber::e
            )

        return ld
    }

    override fun onCleared() {
        disposable.dispose()
    }
}