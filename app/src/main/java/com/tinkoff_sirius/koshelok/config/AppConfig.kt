package com.tinkoff_sirius.koshelok.config

import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.entities.Category
import com.tinkoff_sirius.koshelok.entities.Transaction
import com.tinkoff_sirius.koshelok.entities.TransactionType
import com.tinkoff_sirius.koshelok.entities.Types
import com.tinkoff_sirius.koshelok.repository.entities.CategoryData
import com.tinkoff_sirius.koshelok.repository.entities.TransactionData
import com.tinkoff_sirius.koshelok.repository.entities.WalletData
import com.tinkoff_sirius.koshelok.ui.main.adapters.model.MainItem
import kotlinx.datetime.LocalDate

object AppConfig {
    const val WAIT_FOR_PUSH_AGAIN = 3000L
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

//    val mainTransactionsExample = listOf(
//        MainItem.Transaction(
//            500000,
//            Category(TransactionType.INCOME, Types.SALARY.nameType, R.drawable.ic_salary, R.color.green),
//            "2021-08-20".toLocalDate(),
//            "17:16"
//        ),
//        MainItem.Transaction(
//            940,
//            Category(TransactionType.OUTCOME, Types.FOOD.nameType, R.drawable.ic_food, R.color.red),
//            "2021-08-18".toLocalDate(),
//            "18:46"
//        ),
//        MainItem.Transaction(
//            21000,
//            Category(TransactionType.INCOME, Types.ENTERTAINMENT.nameType, R.drawable.ic_sport, R.color.red),
//            "2021-08-17".toLocalDate(),
//            "19:15"
//        ),
//        MainItem.Transaction(
//            8000,
//            Category(TransactionType.INCOME, Types.CLOTHES.nameType, R.drawable.ic_clothes, R.color.main_blue),
//            "2021-08-17".toLocalDate(),
//            "16:15"
//        )
//    )

    val walletExample = WalletData(
        1,
        "Мой кошелек",
        "966.21",
        "63423.32",
        "5423",
        "4000",
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
