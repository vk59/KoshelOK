package com.tinkoff_sirius.koshelok.config

import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.model.Category
import com.tinkoff_sirius.koshelok.model.CategorySealed
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

    val mainTransactionsExample = listOf(
        MainItem.Transaction(
            940,
            CategorySealed.Outcome.Food(),
            "2021-08-19"
        ),
        MainItem.Transaction(
            400000,
            CategorySealed.Income.Salary(),
            "2021-08-18"
        ),
        MainItem.Transaction(
            21000,
            CategorySealed.Income.Present(R.color.red),
            "2021-08-17"
        ),
        MainItem.Transaction(
            8000,
            CategorySealed.Outcome.Clothes(R.color.main_blue),
            "2021-08-17"
        ),

        MainItem.Transaction(
            4000,
            CategorySealed.Outcome.Clothes(R.color.main_blue),
            "2021-08-16"
        ),
        MainItem.Transaction(
            200,
            CategorySealed.Outcome.Sport(),
            "2021-08-16"
        ),
        MainItem.Transaction(
            600000,
            CategorySealed.Income.Capitalization(R.color.red),
            "2021-08-16"
        )
    )

}
