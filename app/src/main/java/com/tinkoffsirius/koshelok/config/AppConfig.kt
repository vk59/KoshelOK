package com.tinkoffsirius.koshelok.config

import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.model.Category
import com.tinkoffsirius.koshelok.model.CategoryType
import com.tinkoffsirius.koshelok.model.Transaction
import com.tinkoffsirius.koshelok.model.Types
import com.tinkoffsirius.koshelok.ui.main.adapters.model.MainItem
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDate

object AppConfig {
    const val WAIT_FOR_PUSH_AGAIN = 3000L
    val headerExample = listOf<MainItem>(
        MainItem.Header("Кошелек 1", "60 000", "170 000", "61 400", "230 000")
    )

    val transactionExample = mutableListOf(
        Transaction(
            6000.0,
            Category(
                CategoryType.INCOME,
                Types.CAPITALIZATION.nameType,
                R.drawable.ic_transaction_category,
                R.color.black
            ),
            LocalDate(2021, 8, 18)
        ),
        Transaction(
            400.0,
            Category(
                CategoryType.INCOME,
                Types.SALARY.nameType,
                R.drawable.ic_transaction_category,
                R.color.black
            ),
            LocalDate(2021, 8, 18)
        ),
    )

    val mainTransactionsExample = listOf(
        MainItem.Transaction(
            500000,
            Category(CategoryType.INCOME, Types.SALARY.nameType, R.drawable.ic_salary, R.color.green),
            "2021-08-20".toLocalDate(),
            "17:16"
        ),
        MainItem.Transaction(
            940,
            Category(CategoryType.OUTCOME, Types.FOOD.nameType, R.drawable.ic_food, R.color.red),
            "2021-08-18".toLocalDate(),
            "18:46"
        ),
        MainItem.Transaction(
            21000,
            Category(CategoryType.INCOME, Types.ENTERTAINMENT.nameType, R.drawable.ic_sport, R.color.red),
            "2021-08-17".toLocalDate(),
            "19:15"
        ),
        MainItem.Transaction(
            8000,
            Category(CategoryType.INCOME, Types.CLOTHES.nameType, R.drawable.ic_clothes, R.color.main_blue),
            "2021-08-17".toLocalDate(),
            "16:15"
        )
    )
}
