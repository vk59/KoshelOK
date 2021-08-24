package com.tinkoffsirius.koshelok.ui.transactionediting

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.databinding.ItemTransactionEditingBinding
import android.util.TypedValue




class TransactionItemEditingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRef: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRef) {

    private val binding by viewBinding(ItemTransactionEditingBinding::bind)

    val buttonText: TextView by lazy { findViewById(R.id.button_text) }

    init {
        inflate(context, R.layout.item_transaction_editing, this)

        val ta: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.TransactionItemEditingView, 0, 0)
        binding.transEditingSubTitle.text =
            ta.getString(R.styleable.TransactionItemEditingView_tie_text)
        buttonText.text = ta.getString(R.styleable.TransactionItemEditingView_button_text)
        ta.recycle()

        val outValue = TypedValue()
        getContext().theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
        setBackgroundResource(outValue.resourceId)
    }
}
