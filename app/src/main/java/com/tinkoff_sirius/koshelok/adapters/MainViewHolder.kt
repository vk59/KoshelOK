package com.tinkoff_sirius.koshelok.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(vararg data: Any)
}