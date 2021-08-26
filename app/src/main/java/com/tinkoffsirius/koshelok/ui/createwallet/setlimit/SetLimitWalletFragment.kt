package com.tinkoffsirius.koshelok.ui.createwallet.setlimit

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.appComponent
import com.tinkoffsirius.koshelok.databinding.FragmentSetLimitWalletBinding
import com.tinkoffsirius.koshelok.di.modules.ViewModelFactory
import com.tinkoffsirius.koshelok.ui.createwallet.CreateWalletViewModel
import timber.log.Timber
import javax.inject.Inject

class SetLimitWalletFragment : Fragment() {

    private val binding by viewBinding(FragmentSetLimitWalletBinding::bind)

    @Inject
    lateinit var createWalletViewModelFactory: ViewModelFactory

    private val createViewModel: CreateWalletViewModel by viewModels(
        factoryProducer = { createWalletViewModelFactory }
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

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

        binding.walletLimitEdit.addTextChangedListener {
            binding.setLimitButton.isEnabled = isValidText(it)
        }

        binding.setLimitButton.setOnClickListener {

            if (!binding.walletLimitEdit.text?.trim()
                    .isNullOrEmpty() && !binding.walletLimitEdit.text?.toString()
                    .equals(".")
            ) {
                val limit = binding.walletLimitEdit.text.toString()
                createViewModel.updateLimit(limit)
                    .observe(viewLifecycleOwner) {
                        Timber.d("Successfully updated limit $limit")
                        navController.navigate(R.id.action_setLimitWalletFragment_to_walletEditingFragment)
                    }
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }
    }

    private fun isValidText(editable: Editable?) =
        (editable?.isEmpty()!! || (editable.isNotEmpty() && (editable.get(0) ?: ".") != "."))
}
