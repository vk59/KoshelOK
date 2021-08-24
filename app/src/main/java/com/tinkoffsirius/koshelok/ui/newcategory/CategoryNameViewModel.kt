package com.tinkoffsirius.koshelok.ui.newcategory

import androidx.lifecycle.ViewModel
import com.tinkoffsirius.koshelok.repository.PosedTransactionSharedRepository
import com.tinkoffsirius.koshelok.repository.WalletRepository

class CategoryNameViewModel(
    private val transactionRepository: PosedTransactionSharedRepository,
    private val walletRepository: WalletRepository
) : ViewModel() {

}