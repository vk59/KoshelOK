package com.tinkoffsirius.koshelok.ui.createwallet.setcurrency

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
import com.tinkoffsirius.koshelok.databinding.FragmentSetCurrencyBinding
import com.tinkoffsirius.koshelok.entities.Currency
import com.tinkoffsirius.koshelok.ui.createwallet.CreateWalletViewModel

class SetCurrencyFragment : Fragment() {

    private val binding by viewBinding(FragmentSetCurrencyBinding::bind)

    private val createViewModel: CreateWalletViewModel by viewModels(
        factoryProducer = { Dependencies.createWalletViewModelFactory }
    )

    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_set_currency, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initButtons()
    }

    private fun initButtons() {
        binding.toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }

        binding.setCurrency.setOnClickListener {
            navController.navigate(R.id.action_setCurrencyFragment_to_walletEditingFragment)
        }
    }

    private fun initRecycler() {
        val currencyAdapter = CurrencyAdapter {
            createViewModel.updateCurrency(currency = it)
                .observe(viewLifecycleOwner, {
                    binding.setCurrency.isEnabled = true
                })
        }

        currencyAdapter.setData(Currency.values().toList())

        binding.currencyRecycler.apply {
            adapter = currencyAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}
