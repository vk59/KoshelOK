package com.tinkoffsirius.koshelok.repository

import com.tinkoffsirius.koshelok.repository.entities.CategoryData
import com.tinkoffsirius.koshelok.repository.entities.CreateTransactionData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface TransactionEditingRepository {

    fun createTransaction(transactionData: CreateTransactionData): Completable

    fun getIncomeCategories(idUser: Long): Single<List<CategoryData>>

    fun getOutcomeCategories(idUser: Long): Single<List<CategoryData>>

    fun createCategory(category: CategoryData): Completable

    fun updateTransaction(transactionData: CreateTransactionData): Completable
}
