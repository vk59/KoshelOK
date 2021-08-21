package com.tinkoffsirius.koshelok.ui

import android.content.Context
import androidx.annotation.StringRes

class ResourceProvider(private val context: Context) {
    fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    fun getColor(colorId: Int): Int {
        return context.getColor(colorId)
    }
}