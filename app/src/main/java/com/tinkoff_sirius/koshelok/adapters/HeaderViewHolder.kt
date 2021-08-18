package com.tinkoff_sirius.koshelok.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.model.MainItem.Header

class HeaderViewHolder(view: View) : MainViewHolder(view) {

    private val walletCurrentMoney: TextView by lazy {
        itemView.findViewById(R.id.walletCurrentMoney)
    }

    private val walletName: TextView by lazy {
        itemView.findViewById(R.id.walletName)
    }

    private val cardIncome: CardView by lazy {
        itemView.findViewById(R.id.cardIncome)
    }

    private val cardOutcome: CardView by lazy {
        itemView.findViewById(R.id.cardOutcome)
    }

    override fun bind(data: Any) {
        if (data is Header) {
            walletName.text = data.walletName
            walletCurrentMoney.text = "${data.currentMoney} ₽"

            cardIncome.findViewById<TextView>(R.id.typeCard).text =
                itemView.context.resources.getString(R.string.card_income_title)

            cardIncome.findViewById<ImageView>(R.id.icon).setImageResource(R.drawable.ic_green_point)

            cardIncome.findViewById<TextView>(R.id.textMoneyCard).text = "${data.income} ₽"

            cardOutcome.findViewById<TextView>(R.id.typeCard).text =
                itemView.context.resources.getString(R.string.card_outcome_title)

            cardOutcome.findViewById<ImageView>(R.id.icon).setImageResource(R.drawable.ic_green_point)
            cardOutcome.findViewById<TextView>(R.id.textMoneyCard).text = "${data.outcome} ₽"
        }

    }
}
