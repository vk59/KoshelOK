package com.tinkoff_sirius.koshelok.ui.transaction_editing

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.tinkoff_sirius.koshelok.R

class TransactionItemEditingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRef: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRef) {

    val header: TextView by lazy { findViewById(R.id.trans_editing_sub_title) }

    init {
        inflate(context, R.layout.item_transaction_editing, this)

        val ta: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.TransactionItemEditingView, 0, 0);
        header.text = ta.getString(R.styleable.TransactionItemEditingView_tie_text)
        ta.recycle()
    }
}
