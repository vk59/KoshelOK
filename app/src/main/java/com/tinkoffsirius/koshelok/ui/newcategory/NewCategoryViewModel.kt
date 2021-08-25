package com.tinkoffsirius.koshelok.ui.newcategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoffsirius.koshelok.entities.Category
import com.tinkoffsirius.koshelok.entities.TransactionType
import com.tinkoffsirius.koshelok.repository.shared.NewCategorySharedRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class NewCategoryViewModel @Inject constructor(
    private val newCategorySharedRepository: NewCategorySharedRepository
) : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    val newCategory: MutableLiveData<Category> = MutableLiveData()

    init {
        updateState()
    }

    fun updateState() {
        disposable += newCategorySharedRepository.getNewCategory()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { newCategory.value = it },
                onError = { Timber.e(it) }
            )
    }

    fun removeTransaction() {
        newCategorySharedRepository.removeNewCategory()
    }

    fun updateNewCategoryType(type: TransactionType): LiveData<Unit> {
        val ld = MutableLiveData<Unit>()

        disposable += newCategorySharedRepository.getNewCategory()
            .map { it.copy(typeName = type) }
            .doOnSuccess { newCategory.value = it }
            .flatMapCompletable { newCategorySharedRepository.saveNewCategory(it) }
            .subscribeBy(
                onComplete = { ld.value = Unit },
                onError = Timber::e
            )

        return ld
    }

    fun updateNewCategoryName(name: String): LiveData<Unit> {
        val ld = MutableLiveData<Unit>()

        disposable += newCategorySharedRepository.getNewCategory()
            .map { it.copy(categoryName = name) }
            .doOnSuccess { newCategory.value = it }
            .flatMapCompletable { newCategorySharedRepository.saveNewCategory(it) }
            .subscribeBy(
                onComplete = { ld.value = Unit },
                onError = Timber::e
            )

        return ld
    }
}
