package com.tinkoffsirius.koshelok.ui

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class ResourceProvider(private val context: Context) {
    fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    fun getColor(@ColorRes colorId: Int): Int {
        return ContextCompat.getColor(context, colorId)
    }
}
