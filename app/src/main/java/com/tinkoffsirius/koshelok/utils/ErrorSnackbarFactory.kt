package com.tinkoffsirius.koshelok.utils

import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.tinkoffsirius.koshelok.R

class ErrorSnackbarFactory(
    private val view: View
) {
    fun create(icon: Int, text: String): Snackbar {
        val snackbar = Snackbar.make(view, "   $text", Snackbar.LENGTH_LONG)
        val view = snackbar.view
        val sbTextView = view.findViewById<TextView>(R.id.snackbar_text)
        sbTextView.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0)
        sbTextView.setTextColor(view.context.getColor(R.color.black))
        snackbar.setBackgroundTint(view.context.getColor(R.color.white))
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        return snackbar
    }
}