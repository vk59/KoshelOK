package com.tinkoffsirius.koshelok.config

import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.entities.Category
import com.tinkoffsirius.koshelok.entities.Transaction
import com.tinkoffsirius.koshelok.entities.TransactionType
import com.tinkoffsirius.koshelok.entities.Types
import com.tinkoffsirius.koshelok.repository.entities.CategoryData
import com.tinkoffsirius.koshelok.repository.entities.TransactionData
import com.tinkoffsirius.koshelok.repository.entities.WalletData
import com.tinkoffsirius.koshelok.ui.main.adapters.model.MainItem
import kotlinx.datetime.LocalDate

object AppConfig {
    val headerExample = listOf<MainItem>(
        MainItem.Header("Кошелек 1", "60 000", "170 000", "61 400", "230 000")
    )

    val transactionExample = mutableListOf(
        Transaction(
            6000.0.toString(),
            Category(
                1,
                TransactionType.INCOME,
                Types.CAPITALIZATION.nameType,
                R.drawable.ic_transaction_category,
                R.color.black
            ),
            LocalDate(2021, 8, 18)
        ),
        Transaction(
            400.0.toString(),
            Category(
                2,
                TransactionType.INCOME,
                Types.SALARY.nameType,
                R.drawable.ic_transaction_category,
                R.color.black
            ),
            LocalDate(2021, 8, 18)
        ),
    )

    val walletExample = WalletData(
        1,
        "Мой кошелек",
        "966.21",
        "63423.32",
        "5423",
        "40000",
        "RUB",
        listOf(
            TransactionData(
                1,
                "994.00",
                TransactionType.OUTCOME.name,
                CategoryData(1, "Супермаркеты", R.color.green, R.drawable.ic_food),
                "2021-08-20T22:34",
                "RUB"
            ),
            TransactionData(
                2,
                "6000.00",
                TransactionType.OUTCOME.name,
                CategoryData(2, "Спорт", R.color.red, R.drawable.ic_sport),
                "2021-08-19T23:59",
                "RUB"
            ),
            TransactionData(
                3,
                "90000.00",
                TransactionType.INCOME.name,
                CategoryData(3, "Зарплата", R.color.red, R.drawable.ic_salary),
                "2021-08-19T23:59",
                "RUB"
            )
        )
    )

    val categoriesExample = listOf<CategoryData>()
}