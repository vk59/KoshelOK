package com.tinkoffsirius.koshelok.repository

import com.tinkoffsirius.koshelok.repository.entities.CategoryData
import com.tinkoffsirius.koshelok.repository.entities.CreateTransactionData
import com.tinkoffsirius.koshelok.service.WalletService
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class TransactionEditingRepositoryImpl @Inject constructor() : TransactionEditingRepository {

    @Inject
    lateinit var walletService: WalletService

    override fun createTransaction(transactionData: CreateTransactionData): Completable {
        return walletService.createTransaction(transactionData)
    }

    override fun getIncomeCategories(idUser: Long): Single<List<CategoryData>> {
        return walletService.getIncomeCategories(idUser)
    }

    override fun getOutcomeCategories(idUser: Long): Single<List<CategoryData>> {
        return walletService.getSpendingCategories(idUser)
    }

    override fun createCategory(category: CategoryData): Completable {
        return walletService.createCategory(category)
    }

    override fun updateTransaction(transactionData: CreateTransactionData): Completable {
        return walletService.updateTransaction(transactionData)
    }
}