package com.tinkoffsirius.koshelok.repository

import com.tinkoffsirius.koshelok.repository.entities.CategoryData
import io.reactivex.rxjava3.core.Completable

interface NewCategoryRepository {

    fun createNewCategory(categoryData: CategoryData): Completable
}