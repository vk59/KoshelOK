package com.tinkoff_sirius.koshelok.adapters

import android.view.View
import android.widget.TextView
import com.tinkoff_sirius.koshelok.R

class TransactionViewHolder(private val itemView: View) :
    MainViewHolder(itemView) {
//    private val titleView: TextView by lazy(LazyThreadSafetyMode.NONE) {
//
//    }

    override fun bind(data: Any) {
        itemView.findViewById<TextView>(R.id.title).text = data.toString()

    }
}
