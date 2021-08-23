package com.tinkoffsirius.koshelok.ui.walletlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.Dependencies
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.config.AppConfig
import com.tinkoffsirius.koshelok.databinding.FragmentWalletListBinding

class WalletListFragment : Fragment() {

    private val binding by viewBinding(FragmentWalletListBinding::bind)

    private val navController by lazy { findNavController() }

    private val walletListViewModel: WalletListViewModel by viewModels(
        factoryProducer = { Dependencies.walletListViewModelFactory }
    )

    private val walletRecyclerAdapter by lazy { WalletRecyclerAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_wallet_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAppbar()
        initRecycler()
    }

    private fun initRecycler() {
        binding.walletsRecycler.apply {
            adapter = walletRecyclerAdapter
            layoutManager = LinearLayoutManager(this.context)
        }

        binding.textNoEntities.visibility = View.GONE
        walletRecyclerAdapter.setData(AppConfig.walletsExample.map { walletData ->
            WalletItem(walletData.id, walletData.name, walletData.balance, walletData.currencyType)
        })
    }

    private fun initAppbar() {
        binding.apply {
            cardIncome.icon.setImageResource(R.drawable.ic_green_point)
            cardIncome.typeCard.text = "Общий доход"
            cardOutcome.icon.setImageResource(R.drawable.ic_red_point)
            cardOutcome.typeCard.text = "Общий расход"
            walletListViewModel.userInfoBalance.observe(viewLifecycleOwner) {
                overallBalance.text = it.overallBalance
                cardIncome.textMoneyCard.text = it.overallIncome
                cardOutcome.textMoneyCard.text = it.overallOutcome
            }
        }
    }
}
