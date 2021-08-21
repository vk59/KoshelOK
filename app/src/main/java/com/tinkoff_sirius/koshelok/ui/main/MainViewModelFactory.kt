package com.tinkoff_sirius.koshelok.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tinkoff_sirius.koshelok.repository.AccountSharedRepository
import com.tinkoff_sirius.koshelok.repository.WalletRepository

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