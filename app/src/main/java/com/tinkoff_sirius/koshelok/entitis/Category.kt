package com.tinkoff_sirius.koshelok.entitis

sealed class Category(open val type: String, open val icon: Int, open val color: Int) {

    class Income(override val type: String, override val icon: Int, override val color: Int)
        : Category(type, icon, color)

    class Outcome(override val type: String, override val icon: Int, override val color: Int)
        : Category(type, icon, color)
}
