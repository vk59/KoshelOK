package com.tinkoff_sirius.koshelok.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff_sirius.koshelok.model.MainItem

abstract class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: MainItem)
}