package com.tinkoffsirius.koshelok.ui.walletlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tinkoffsirius.koshelok.repository.AccountSharedRepository
import com.tinkoffsirius.koshelok.repository.WalletRepository

class WalletsListViewModelFactory(
    private val accountRepository: AccountSharedRepository,
    private val repository: WalletRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WalletListViewModel(
            accountRepository,
            repository
        ) as T
    }
}
