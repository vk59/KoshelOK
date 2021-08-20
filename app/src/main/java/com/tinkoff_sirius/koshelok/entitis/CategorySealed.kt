package com.tinkoff_sirius.koshelok.entitis

import com.tinkoff_sirius.koshelok.R

sealed class CategorySealed {

    abstract val color: Int
    abstract val type: String
    abstract val icon: Int

    sealed class Income : CategorySealed() {
        class Salary(override val color: Int = R.color.green) : Income() {
            override val type: String
                get() = Types.SALARY.nameType
            override val icon: Int
                get() = R.drawable.ic_salary
        }

        class PartTimeJob(override val color: Int = R.color.green) : Income() {
            override val type: String
                get() = Types.PART_WORK_JOB.nameType
            override val icon: Int
                get() = R.drawable.ic_salary
        }

        class Present(override val color: Int = R.color.green) : Income() {
            override val type: String
                get() = Types.PRESENT.nameType
            override val icon: Int
                get() = R.drawable.ic_present
        }

        class Capitalization(override val color: Int = R.color.green) : Income() {
            override val type: String
            get() = Types.CAPITALIZATION.nameType
            override val icon: Int
            get() = R.drawable.ic_capitalize
        }
    }

    sealed class Outcome : CategorySealed() {
        class Food(override val color: Int = R.color.green) : Outcome() {
            override val type: String
                get() = Types.FOOD.nameType
            override val icon: Int
                get() = R.drawable.ic_food
        }

        class Clothes(override val color: Int = R.color.green) : Outcome() {
            override val type: String
                get() = Types.CLOTHES.nameType
            override val icon: Int
                get() = R.drawable.ic_clothes
        }

        class Entertainment(override val color: Int = R.color.green) : Outcome() {
            override val type: String
                get() = Types.ENTERTAINMENT.nameType
            override val icon: Int
                get() = R.drawable.ic_present
        }

        class Sport(override val color: Int = R.color.green) : Outcome() {
            override val type: String
                get() = Types.SPORT.nameType
            override val icon: Int
                get() = R.drawable.ic_sport
        }
    }
}
