package com.tinkoff_sirius.koshelok.config

import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.model.Category
import com.tinkoff_sirius.koshelok.model.Transaction
import com.tinkoff_sirius.koshelok.model.Types
import com.tinkoff_sirius.koshelok.ui.main.adapters.model.MainItem
import kotlinx.datetime.LocalDate

object AppConfig {
    const val WAIT_FOR_PUSH_AGAIN = 3000L
    val headerExample = listOf<MainItem>(
        MainItem.Header("Кошелек 1", "60 000", "170 000", "61 400", "230 000")
    )

    val transactionExample = mutableListOf(
        Transaction(
            6000.0,
            Category.Income(
                Types.CAPITALIZATION,
                R.drawable.ic_transaction_category,
                R.color.black
            ),
            LocalDate(2021, 8, 18)
        ),
        Transaction(
            400.0,
            Category.Income(
                Types.SALARY,
                R.drawable.ic_transaction_category,
                R.color.black
            ),
            LocalDate(2021, 8, 18)
        ),
    )


}
