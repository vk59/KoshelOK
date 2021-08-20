package com.tinkoff_sirius.koshelok.ui.transaction_editing

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoff_sirius.koshelok.entitis.PosedTransaction
import com.tinkoff_sirius.koshelok.repository.PosedTransactionSharedRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class TransactionEditingViewModel(transactionSharedRepository: PosedTransactionSharedRepository) : ViewModel() {

    val transaction: MutableLiveData<PosedTransaction> = MutableLiveData()

    init {
        transactionSharedRepository.getTransaction()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy (
                onSuccess = {transaction.value = it} ,
                onError = { Log.d("ERROR" , it.toString())}
            )
    }
}