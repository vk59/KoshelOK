package com.tinkoffsirius.koshelok.utils

object ColorProvider {

    fun getColorResId(rgb: Int): Int {
        return Colors.values().first { it.rgb == rgb }.res
    }

}