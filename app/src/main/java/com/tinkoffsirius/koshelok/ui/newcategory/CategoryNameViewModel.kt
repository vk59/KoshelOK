package com.tinkoffsirius.koshelok.ui.newcategory

import androidx.lifecycle.ViewModel
import com.tinkoffsirius.koshelok.di.Mocked
import com.tinkoffsirius.koshelok.repository.main.WalletRepository
import com.tinkoffsirius.koshelok.repository.shared.PosedTransactionSharedRepository
import javax.inject.Inject

class CategoryNameViewModel @Inject constructor(
    private val transactionRepository: PosedTransactionSharedRepository,
    @Mocked
    private val walletRepositoryImpl: WalletRepository
) : ViewModel()

