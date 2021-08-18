package com.tinkoff_sirius.koshelok.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff_sirius.koshelok.R

class HeaderViewHolder(view: View) : MainViewHolder(view) {

    private val walletCount: TextView = itemView.findViewById(R.id.wallet_count)

    override fun bind(vararg data: Any) {
       this.walletCount.text = walletCount.toString()
    }

}