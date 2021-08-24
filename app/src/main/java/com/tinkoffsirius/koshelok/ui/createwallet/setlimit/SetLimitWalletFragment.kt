package com.tinkoffsirius.koshelok.ui.createwallet.setlimit

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.Dependencies.createWalletViewModelFactory
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.databinding.FragmentSetLimitWalletBinding
import com.tinkoffsirius.koshelok.ui.createwallet.CreateWalletViewModel
import timber.log.Timber

class SetLimitWalletFragment : Fragment() {

    private val binding by viewBinding(FragmentSetLimitWalletBinding::bind)

    private val createViewModel: CreateWalletViewModel by viewModels(
        factoryProducer = { createWalletViewModelFactory }
    )

    private val navController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_set_limit_wallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (binding.walletLimitEdit.requestFocus()) {
            (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .toggleSoftInput(
                    0, InputMethodManager.HIDE_IMPLICIT_ONLY
                )
        }

        binding.walletLimitEdit.doOnTextChanged { text, start, before, count ->
            binding.setLimitButton.isEnabled = (text != "")
        }

        binding.setLimitButton.setOnClickListener {
            val limit = binding.walletLimitEdit.text.toString()
            createViewModel.updateLimit(limit)
                .observe(viewLifecycleOwner) {
                    Timber.d("Successfully updated limit $limit")
                    navController.navigate(R.id.action_setLimitWalletFragment_to_walletEditingFragment)
                }
        }

        binding.toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }
    }
}
