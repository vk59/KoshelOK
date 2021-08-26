package com.tinkoffsirius.koshelok.config

import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.entities.Category
import com.tinkoffsirius.koshelok.entities.TransactionType
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
            id = 1,
            name = Types.CAPITALIZATION.nameType,
            color = R.color.red,
            icon = R.drawable.ic_capitalize,
            "",
            "",
            0
        ),
        CategoryData(
            id = 2,
            name = Types.SALARY.nameType,
            color = R.color.red,
            icon = R.drawable.ic_salary,
            "",
            "",
            0
        ),
        CategoryData(
            id = 3,
            name = Types.PART_WORK_JOB.nameType,
            color = R.color.red,
            icon = R.drawable.ic_capitalize,
            "",
            "",
            0
        )
    )

    val transactionExample = mutableListOf(

        Category(
            id = 1,
            typeName = TransactionType.INCOME,
            categoryName = Types.CAPITALIZATION.nameType,
            icon = R.drawable.ic_sport,
            color = R.color.green
        ),

        Category(
            id = 3,
            typeName = TransactionType.INCOME,
            categoryName = Types.SALARY.nameType,
            icon = R.drawable.ic_salary,
            color = R.color.green
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
                id = 1,
                amount = "994.00",
                transactionType = TransactionType.OUTCOME.name,
                category = CategoryData(
                    id = 1,
                    name = "Супермаркеты",
                    color = R.color.green,
                    icon = R.drawable.ic_food,
                    "",
                    "",
                    0
                ),
                date = "2021-08-20T22:34",
                currency = "RUB"
            ),
            TransactionData(
                2,
                "6000.00",
                TransactionType.OUTCOME.name,
                CategoryData(
                    id = 2,
                    name = "Спорт",
                    color = R.color.red,
                    icon = R.drawable.ic_sport,
                    "",
                    "",
                    0
                ),
                "2021-08-19T23:59",
                "RUB"
            ),
            TransactionData(
                id = 3,
                amount = "90000.00",
                transactionType = TransactionType.INCOME.name,
                category = CategoryData(
                    id = 3,
                    name = "Зарплата",
                    color = R.color.red,
                    icon = R.drawable.ic_salary,
                    "",
                    "",
                    0
                ),
                date = "2021-08-19T23:59",
                currency = "RUB"
            )
        )
    )

    val walletsExample = MutableList(12) { walletExample }
}
