package com.tinkoff_sirius.koshelok.ui.main.adapters.view_holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff_sirius.koshelok.ui.main.adapters.model.MainItem

abstract class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: MainItem)
}
