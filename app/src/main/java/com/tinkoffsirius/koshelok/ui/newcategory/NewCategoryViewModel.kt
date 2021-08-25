package com.tinkoffsirius.koshelok.ui.newcategory

import androidx.annotation.ColorInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoffsirius.koshelok.Icons
import com.tinkoffsirius.koshelok.entities.Category
import com.tinkoffsirius.koshelok.entities.Icon
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

    val icons: MutableLiveData<List<Icon>> = MutableLiveData(emptyList())

    init {
        updateState()

        icons.value = Icons.values().map { Icon(it.drawableId, -10996754) }
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

    fun updateNewCategoryIcon(iconId: Int): LiveData<Unit> {
        val ld = MutableLiveData<Unit>()

        disposable += newCategorySharedRepository.getNewCategory()
            .map { it.copy(icon = iconId) }
            .doOnSuccess { newCategory.value = it }
            .flatMapCompletable { newCategorySharedRepository.saveNewCategory(it) }
            .subscribeBy(
                onComplete = { ld.value = Unit },
                onError = Timber::e
            )

        return ld
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

    fun updateNewCategoryColor(@ColorInt color: Int) {
        disposable += newCategorySharedRepository.getNewCategory()
            .map { it.copy(color = color) }
            .doOnSuccess { newCategory.value = it }
            .flatMapCompletable { newCategorySharedRepository.saveNewCategory(it) }
            .subscribeBy(
                onComplete = {
                    icons.value = Icons.values().map { Icon(it.drawableId, color) }
                },
                onError = Timber::e
            )
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
