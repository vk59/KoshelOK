package com.tinkoffsirius.koshelok.ui.walletlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tinkoffsirius.koshelok.repository.AccountSharedRepository
import com.tinkoffsirius.koshelok.repository.WalletRepository
import com.tinkoffsirius.koshelok.repository.WalletSharedRepository

class WalletsListViewModelFactory(
    private val accountRepository: AccountSharedRepository,
    private val repository: WalletRepository,
    private val walletSharedRepository: WalletSharedRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WalletListViewModel(
            accountRepository,
            repository,
            walletSharedRepository
        ) as T
    }
}
