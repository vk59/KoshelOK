package com.tinkoffsirius.koshelok.ui.newcategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tinkoffsirius.koshelok.repository.NewCategorySharedRepository
import com.tinkoffsirius.koshelok.ui.main.MainViewModel

class NewCategoryViewModelFactory(private val newCategorySharedRepository: NewCategorySharedRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewCategoryViewModel(
           newCategorySharedRepository
        ) as T
    }
}
