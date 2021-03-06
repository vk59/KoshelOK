package com.tinkoffsirius.koshelok.ui.newcategory

import androidx.annotation.ColorInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoffsirius.koshelok.config.toCategoryData
import com.tinkoffsirius.koshelok.entities.Category
import com.tinkoffsirius.koshelok.entities.Icon
import com.tinkoffsirius.koshelok.entities.TransactionType
import com.tinkoffsirius.koshelok.repository.NewCategoryRepository
import com.tinkoffsirius.koshelok.repository.shared.AccountSharedRepository
import com.tinkoffsirius.koshelok.repository.shared.NewCategorySharedRepository
import com.tinkoffsirius.koshelok.repository.shared.PosedTransactionSharedRepository
import com.tinkoffsirius.koshelok.utils.Event
import com.tinkoffsirius.koshelok.utils.Icons
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.Singles
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import timber.log.Timber
import javax.inject.Inject

class NewCategoryViewModel @Inject constructor(
    private val accountSharedRepository: AccountSharedRepository,
    private val posedTransactionSharedRepository: PosedTransactionSharedRepository,
    private val newCategorySharedRepository: NewCategorySharedRepository,
    private val newCategoryRepository: NewCategoryRepository
) : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    val newCategory: MutableLiveData<Category> = MutableLiveData()

    val icons: MutableLiveData<List<Icon>> = MutableLiveData(emptyList())

    val status: MutableLiveData<Event?> = MutableLiveData(null)

    init {
        updateState()
        updateNewCategoryColor(-10996754)
        updateNewCategoryName("Новая категория")
        updateNewCategoryIcon(Icons.CLOTHES.drawableId)
        icons.value = Icons.values().map { Icon(it.drawableId, -10996754) }
    }

    fun updateState() {
        disposable += posedTransactionSharedRepository.getTransaction()
            .flatMap { transaction ->
                newCategorySharedRepository.getNewCategory(TransactionType.valueOf(transaction.type))
            }
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

        disposable +=
            posedTransactionSharedRepository.getTransaction()
                .flatMap { transaction ->
                    newCategorySharedRepository.getNewCategory(TransactionType.valueOf(transaction.type))
                }
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

    fun createCategory() {
        status.postValue(Event.Loading())
        Singles.zip(
            newCategorySharedRepository.getNewCategory(),
            accountSharedRepository.getUserInfo()
        )
            .flatMapCompletable { (newCategory, userInfo) ->
                newCategoryRepository.createNewCategory(
                    categoryData = newCategory.toCategoryData(
                        creationDate = Clock.System.now()
                            .toLocalDateTime(TimeZone.currentSystemDefault()),
                        userId = userInfo.id!!,
                        colorIsFromDrawable = false
                    )
                )
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onComplete = { status.postValue(Event.Success()) },
                onError = {
                    Timber.e(it)
                    status.postValue(Event.Error(it))
                }
            )
    }
}
