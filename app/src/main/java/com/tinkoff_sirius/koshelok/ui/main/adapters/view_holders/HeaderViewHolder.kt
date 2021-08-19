package com.tinkoff_sirius.koshelok.ui.main.adapters.view_holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.databinding.ItemHomeHeaderBinding
import com.tinkoff_sirius.koshelok.ui.main.adapters.model.MainItem
import com.tinkoff_sirius.koshelok.ui.main.adapters.model.MainItem.Header

class HeaderViewHolder(val view: View) : MainViewHolder(view) {

    private val binding: ItemHomeHeaderBinding by viewBinding(ItemHomeHeaderBinding::bind)

    private val walletCurrentMoney: TextView by lazy {
        binding.walletCurrentMoney
    }

    private val walletName: TextView by lazy {
        binding.walletName
    }

    private val cardIncome: CardView by lazy {
        binding.cardIncome.root
    }

    private val cardOutcome: CardView by lazy {
        binding.cardOutcome.root
    }

    override fun bind(data: MainItem) {
        if (data is Header) {
            walletName.text = data.walletName
            walletCurrentMoney.text = view.context.getString(R.string.rubles, data.currentMoney)

            cardIncome.findViewById<TextView>(R.id.typeCard).text =
                view.context.resources.getString(R.string.card_income_title)

            cardIncome.findViewById<ImageView>(R.id.icon).setImageResource(R.drawable.ic_green_point)

            cardIncome.findViewById<TextView>(R.id.textMoneyCard).text =
                view.context.getString(R.string.rubles, data.income)

            cardIncome.findViewById<TextView>(R.id.textMaxMoneyCard).visibility = View.GONE

            cardOutcome.findViewById<TextView>(R.id.typeCard).text =
                view.context.resources.getString(R.string.card_outcome_title)

            cardOutcome.findViewById<ImageView>(R.id.icon).setImageResource(R.drawable.ic_green_point)
            cardOutcome.findViewById<TextView>(R.id.textMoneyCard).text =
                view.context.resources.getString(R.string.rubles, data.outcome)

            cardOutcome.findViewById<TextView>(R.id.textMaxMoneyCard).text =
                "/${data.maxOutcome} ₽"

        }
    }
}
