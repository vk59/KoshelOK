package com.tinkoffsirius.koshelok.ui.createwallet.editwallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.Dependencies
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.databinding.FragmentWalletEditingBinding
import com.tinkoffsirius.koshelok.ui.createwallet.CreateWalletViewModel

class WalletEditingFragment : Fragment() {

    private val createViewModel: CreateWalletViewModel by viewModels(
        factoryProducer = { Dependencies.createWalletViewModelFactory }
    )

    private val binding by viewBinding(FragmentWalletEditingBinding::bind)

    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wallet_editing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createViewModel.wallet.observe(viewLifecycleOwner) {
            binding.walletEditingNameLabel.buttonText.text = it.name
            binding.currency.buttonText.text = it.currencyType
            binding.limit.buttonText.text =
                if (it.limit == "") {
                    Dependencies.resourceProvider.getString(R.string.limit_not_stated)
                } else { it.limit }
        }

        initListeners()
    }

    private fun initListeners() {
        binding.toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }

        binding.walletEditingNameLabel.setOnClickListener {
            navController
                .navigate(R.id.action_walletEditingFragment_to_setNameWalletFragment)
        }

        binding.currency.setOnClickListener {
            navController.navigate(R.id.action_walletEditingFragment_to_setCurrencyFragment)
        }

        binding.limit.setOnClickListener {
            navController.navigate(R.id.action_walletEditingFragment_to_setLimitWalletFragment)
        }

        binding.createWalletButton.setOnClickListener {
            createViewModel.saveWallet().observe(viewLifecycleOwner) {
                navController.navigate(R.id.action_walletEditingFragment_to_mainFragment)
            }
        }
    }
}
