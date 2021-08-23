package com.tinkoffsirius.koshelok.ui.createwallet.setname

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tinkoffsirius.koshelok.repository.AccountSharedRepository
import com.tinkoffsirius.koshelok.repository.WalletRepository
import com.tinkoffsirius.koshelok.repository.WalletSharedRepository

class CreateWalletViewModelFactory(
    private val accountRepository: AccountSharedRepository,
    private val walletSharedRepository: WalletSharedRepository,
    private val repository: WalletRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreateWalletViewModel(
            accountRepository,
            walletSharedRepository,
            repository
        ) as T
    }
}