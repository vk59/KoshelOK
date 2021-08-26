package com.tinkoffsirius.koshelok.repository

import com.tinkoffsirius.koshelok.repository.entities.CategoryData
import com.tinkoffsirius.koshelok.service.WalletService
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class NewCategoryRepositoryImpl @Inject constructor() : NewCategoryRepository {

    @Inject
    lateinit var walletService: WalletService

    override fun createNewCategory(categoryData: CategoryData): Completable {
        return walletService.createCategory(categoryData)
    }
}