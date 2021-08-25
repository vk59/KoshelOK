package com.tinkoffsirius.koshelok.ui

object ColorProvider {

    fun getColorResId(rgb: Int): Int {
        return Colors.values().first { it.rgb == rgb }.res
    }

}