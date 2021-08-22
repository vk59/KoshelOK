package com.tinkoffsirius.koshelok.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tinkoffsirius.koshelok.repository.AccountSharedRepository
import com.tinkoffsirius.koshelok.repository.WalletRepository

class MainViewModelFactory(
    private val accountRepository: AccountSharedRepository,
    private val repository: WalletRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(
            accountRepository,
            repository
        ) as T
    }
}
