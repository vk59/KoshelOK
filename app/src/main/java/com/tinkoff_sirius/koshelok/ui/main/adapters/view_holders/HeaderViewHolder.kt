package com.tinkoff_sirius.koshelok.ui.main.adapters.view_holders

import android.view.View
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.databinding.ItemHomeHeaderBinding
import com.tinkoff_sirius.koshelok.ui.main.adapters.model.MainItem
import com.tinkoff_sirius.koshelok.ui.main.adapters.model.MainItem.Header

class HeaderViewHolder(private val binding: ItemHomeHeaderBinding) : MainViewHolder(binding.root) {

    override fun bind(data: MainItem) {
        if (data is Header) {
            with (binding) {
                walletName.text = data.walletName
                walletCurrentMoney.text = data.currentMoney

                cardIncome.typeCard.text =
                    root.context.resources.getString(R.string.card_income_title)

                cardIncome.icon.setImageResource(R.drawable.ic_green_point)

                cardIncome.textMoneyCard.text = data.income

                cardIncome.textMaxMoneyCard.visibility = View.GONE

                cardOutcome.typeCard.text =
                    root.context.resources.getString(R.string.card_outcome_title)

                cardOutcome.icon.setImageResource(R.drawable.ic_green_point)
                cardOutcome.textMoneyCard.text = data.outcome

                cardOutcome.textMaxMoneyCard.text = "/ ${data.maxOutcome}"
            }
        }
    }
}
