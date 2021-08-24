package com.tinkoffsirius.koshelok.ui.newcategory

import androidx.lifecycle.ViewModel
import com.tinkoffsirius.koshelok.repository.MockWalletRepository
import com.tinkoffsirius.koshelok.repository.PosedTransactionSharedRepository

class CategoryNameViewModel(
    private val transactionRepository: PosedTransactionSharedRepository,
    private val walletRepository: MockWalletRepository
) : ViewModel() {

}