package com.tinkoffsirius.koshelok.ui.newcategory

import androidx.lifecycle.ViewModel
import com.tinkoffsirius.koshelok.repository.shared.PosedTransactionSharedRepository
import javax.inject.Inject

class CategoryNameViewModel @Inject constructor(
    private val transactionRepository: PosedTransactionSharedRepository
) : ViewModel()

