package com.tinkoffsirius.koshelok.ui.main.adapters.viewholders

import android.view.View
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.databinding.ItemHomeHeaderBinding
import com.tinkoffsirius.koshelok.ui.ResourceProvider
import com.tinkoffsirius.koshelok.ui.main.adapters.model.MainItem
import com.tinkoffsirius.koshelok.ui.main.adapters.model.MainItem.Header

class HeaderViewHolder(
    private val binding: ItemHomeHeaderBinding,
    private val resourceProvider: ResourceProvider
) : MainViewHolder(binding.root) {

    override fun bind(data: MainItem) {
        if (data is Header) {
            with(binding) {
                walletName.text = data.walletName
                walletCurrentMoney.text = data.currentMoney

                cardIncome.typeCard.text = resourceProvider.getString(R.string.card_income_title)

                cardIncome.icon.setImageResource(R.drawable.ic_green_point)

                cardIncome.textMoneyCard.text = data.income

                cardIncome.textMaxMoneyCard.visibility = View.GONE

                cardOutcome.typeCard.text = resourceProvider.getString(R.string.card_outcome_title)

                cardOutcome.icon.setImageResource(R.drawable.ic_red_point)
                cardOutcome.textMoneyCard.text = data.outcome

                cardOutcome.textMaxMoneyCard.text = "/ ${data.maxOutcome}"
            }
        }
    }
}
