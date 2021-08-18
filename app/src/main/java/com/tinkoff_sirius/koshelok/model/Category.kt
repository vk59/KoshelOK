package com.tinkoff_sirius.koshelok.model

sealed class Category(open val type: Types, open val icon: Int, open val color: Int) {

    class Income(override val type: Types, override val icon: Int, override val color: Int)
        : Category(type, icon, color)

    class Outcome(override val type: Types, override val icon: Int, override val color: Int)
        : Category(type, icon, color)
}
