package com.tinkoffsirius.koshelok.ui.main.adapters.view_holders

import android.view.View
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.databinding.ItemHomeHeaderBinding
import com.tinkoffsirius.koshelok.ui.main.adapters.model.MainItem
import com.tinkoffsirius.koshelok.ui.main.adapters.model.MainItem.Header

class HeaderViewHolder(private val binding: ItemHomeHeaderBinding) : MainViewHolder(binding.root) {

    override fun bind(data: MainItem) {
        if (data is Header) {
            with (binding) {
                walletName.text = data.walletName
                walletCurrentMoney.text = root.context.getString(R.string.rubles, data.currentMoney)

                cardIncome.typeCard.text =
                    root.context.resources.getString(R.string.card_income_title)

                cardIncome.icon.setImageResource(R.drawable.ic_green_point)

                cardIncome.textMoneyCard.text = root.context.getString(R.string.rubles, data.income)

                cardIncome.textMaxMoneyCard.visibility = View.GONE

                cardOutcome.typeCard.text =
                    root.context.resources.getString(R.string.card_outcome_title)

                cardOutcome.icon.setImageResource(R.drawable.ic_green_point)
                cardOutcome.textMoneyCard.text =
                    root.context.resources.getString(R.string.rubles, data.outcome)

                cardOutcome.textMaxMoneyCard.text =
                    "/${data.maxOutcome} ₽"
            }

        }
    }
}
