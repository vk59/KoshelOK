package com.tinkoffsirius.koshelok.ui.transactionediting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tinkoffsirius.koshelok.repository.AccountSharedRepository
import com.tinkoffsirius.koshelok.repository.PosedTransactionSharedRepository
import com.tinkoffsirius.koshelok.repository.WalletRepository

class TransactionEditingViewModelFactory(
    private val transactionRepository: PosedTransactionSharedRepository,
    private val accountRepository: AccountSharedRepository,
    private val repository: WalletRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TransactionEditingViewModel(
            transactionRepository,
            accountRepository,
            repository
        ) as T
    }
}