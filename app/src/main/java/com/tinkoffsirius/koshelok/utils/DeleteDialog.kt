package com.tinkoffsirius.koshelok.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import com.tinkoffsirius.koshelok.R

class DeleteDialog(
    private val message: String,
    private val positiveAction: (dialog: DialogInterface, value: Int) -> Unit,
    private val negativeAction: (dialog: DialogInterface, value: Int) -> Unit,
    private val context: Context
) {
    fun create(): Dialog {
        val builder = AlertDialog.Builder(context)
        return builder.setMessage(message)
            .setPositiveButton("Удалить") { dialog, value ->
                positiveAction(dialog, value)
            }
            .setNegativeButton("Отмена") { dialog, value ->
                negativeAction(dialog, value)
            }
            .create()
            .apply {
                show()
                getButton(AlertDialog.BUTTON_POSITIVE)
                    .setTextColor(context.getColor(R.color.red))
                getButton(AlertDialog.BUTTON_NEGATIVE)
                    .setTextColor(context.getColor(R.color.light_blue))
            }
    }
}
