package com.tinkoff_sirius.koshelok.adapters

import android.view.View
import android.widget.TextView
import com.tinkoff_sirius.koshelok.R

class TransactionViewHolder(itemView: View) :
    MainViewHolder(itemView) {
    private var mTitleView: TextView? = null


    init {
        mTitleView = itemView.findViewById(R.id.title)

    }

    override fun bind(vararg data: Any) {
        mTitleView?.text = data.toString()

    }
}
