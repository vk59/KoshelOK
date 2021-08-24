package com.tinkoffsirius.koshelok.config

import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.entities.Category
import com.tinkoffsirius.koshelok.entities.TransactionType
import com.tinkoffsirius.koshelok.entities.Types
import com.tinkoffsirius.koshelok.repository.entities.CategoryData
import com.tinkoffsirius.koshelok.repository.entities.TransactionData
import com.tinkoffsirius.koshelok.repository.entities.WalletData
import com.tinkoffsirius.koshelok.ui.main.adapters.model.MainItem

object AppConfig {

    val headerExample = listOf<MainItem>(
        MainItem.Header("Кошелек 1", "60 000", "170 000", "61 400", "230 000")
    )

    val categoriesExample = mutableListOf(
        CategoryData(
            1,
            Types.CAPITALIZATION.nameType,
            R.drawable.ic_capitalize,
            R.color.green
        ),
        CategoryData(
            2,
            Types.SALARY.nameType,
            R.drawable.ic_salary,
            R.color.red
        ),
        CategoryData(
            3,
            Types.PART_WORK_JOB.nameType,
            R.drawable.ic_sport,
            R.color.red
        )
    )

    val transactionExample = mutableListOf(

        Category(
            1,
            TransactionType.INCOME,
            Types.CAPITALIZATION.nameType,
            R.drawable.ic_sport,
            R.color.green
        ),

        Category(
            3,
            TransactionType.INCOME,
            Types.SALARY.nameType,
            R.drawable.ic_salary,
            R.color.green
        ),


        Category(
            2,
            TransactionType.OUTCOME,
            Types.CLOTHES.nameType,
            R.drawable.ic_clothes,
            R.color.red

        ),

        Category(
            1,
            TransactionType.INCOME,
            Types.CAPITALIZATION.nameType,
            R.drawable.ic_sport,
            R.color.green
        ),

        Category(
            3,
            TransactionType.INCOME,
            Types.SALARY.nameType,
            R.drawable.ic_salary,
            R.color.green
        ),


        Category(
            2,
            TransactionType.OUTCOME,
            Types.CLOTHES.nameType,
            R.drawable.ic_clothes,
            R.color.red

        ),

        Category(
            1,
            TransactionType.INCOME,
            Types.CAPITALIZATION.nameType,
            R.drawable.ic_sport,
            R.color.green
        ),

        Category(
            3,
            TransactionType.INCOME,
            Types.SALARY.nameType,
            R.drawable.ic_salary,
            R.color.green
        ),


        Category(
            2,
            TransactionType.OUTCOME,
            Types.CLOTHES.nameType,
            R.drawable.ic_clothes,
            R.color.red

        ),

        Category(
            1,
            TransactionType.INCOME,
            Types.CAPITALIZATION.nameType,
            R.drawable.ic_sport,
            R.color.green
        ),

        Category(
            3,
            TransactionType.INCOME,
            Types.SALARY.nameType,
            R.drawable.ic_salary,
            R.color.green
        ),


        Category(
            2,
            TransactionType.OUTCOME,
            Types.CLOTHES.nameType,
            R.drawable.ic_clothes,
            R.color.red
        )
    )

    val walletExample = WalletData(
        1,
        "Мой кошелек",
        "966.21",
        "63423.32",
        "5423",
        "40000",
        "RUB",
        false,
        listOf(
            TransactionData(
                1,
                "994.00",
                TransactionType.OUTCOME.name,
                CategoryData(1, name = "Супермаркеты", icon = R.drawable.ic_food, color = R.color.green),
                "2021-08-20T22:34",
                "RUB"
            ),
            TransactionData(
                2,
                "6000.00",
                TransactionType.OUTCOME.name,
                CategoryData(2, name = "Спорт", icon = R.drawable.ic_sport, color = R.color.red),
                "2021-08-19T23:59",
                "RUB"
            ),
            TransactionData(
                3,
                "90000.00",
                TransactionType.INCOME.name,
                CategoryData(3, name = "Зарплата", icon = R.drawable.ic_salary, color = R.color.red),
                "2021-08-19T23:59",
                "RUB"
            )
        )
    )

    val walletsExample = MutableList(12) { walletExample }
}
