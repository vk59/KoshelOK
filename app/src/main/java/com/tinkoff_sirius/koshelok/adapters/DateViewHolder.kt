package com.tinkoff_sirius.koshelok.adapters

import android.view.View
import android.widget.TextView
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.model.MainItem

class DateViewHolder (view: View) : MainViewHolder(view) {

    private val date: TextView = itemView.findViewById(R.id.date_title)

    override fun bind(data: MainItem) {
    }
}