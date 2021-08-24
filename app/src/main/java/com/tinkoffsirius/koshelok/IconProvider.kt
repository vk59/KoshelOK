package com.tinkoffsirius.koshelok

object IconProvider {

    fun getDrawableInt(idIcon: Int): Int {
        return Icons.values().first { it.id == idIcon }.drawableId
    }

    fun getIconId(drawableId: Int): Int {
        return Icons.values().first { it.drawableId == drawableId }.id
    }
}
