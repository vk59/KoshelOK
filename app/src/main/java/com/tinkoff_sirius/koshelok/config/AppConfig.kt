package com.tinkoff_sirius.koshelok.config

import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.entities.Category
import com.tinkoff_sirius.koshelok.entities.CategoryType
import com.tinkoff_sirius.koshelok.entities.Transaction
import com.tinkoff_sirius.koshelok.entities.Types
import com.tinkoff_sirius.koshelok.repository.entities.CategoryData
import com.tinkoff_sirius.koshelok.repository.entities.TransactionData
import com.tinkoff_sirius.koshelok.repository.entities.WalletData
import com.tinkoff_sirius.koshelok.ui.main.adapters.model.MainItem
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

    val walletExample = WalletData(
        1,
        "Мой кошелек",
        "6043.21",
        "60000.32",
        "54234.79",
        "400000.00",
        "RUB",
        listOf(
            TransactionData(
                1,
                "994.00",
                CategoryType.OUTCOME.name,
                CategoryData(1, "Супермаркеты", R.color.green, R.drawable.ic_food),
                "2021-08-20T22:34",
                "RUB"
            ),
            TransactionData(
                2,
                "6000.00",
                CategoryType.OUTCOME.name,
                CategoryData(2, "Спорт", R.color.red, R.drawable.ic_sport),
                "2021-08-19T23:59",
                "RUB"
            ),
            TransactionData(
                3,
                "90000.00",
                CategoryType.INCOME.name,
                CategoryData(2, "Зарплата", R.color.red, R.drawable.ic_salary),
                "2021-08-19T23:59",
                "RUB"
            )
        )
    )
}
