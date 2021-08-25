package com.tinkoffsirius.koshelok.ui.createwallet.setname

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
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.appComponent
import com.tinkoffsirius.koshelok.databinding.FragmentSetNameWalletBinding
import com.tinkoffsirius.koshelok.di.ViewModelFactory
import com.tinkoffsirius.koshelok.ui.createwallet.CreateWalletViewModel
import timber.log.Timber
import javax.inject.Inject

class SetNameWalletFragment : Fragment() {

    @Inject
    lateinit var createWalletViewModelFactory: ViewModelFactory

    private val createViewModel: CreateWalletViewModel by viewModels(
        factoryProducer = { createWalletViewModelFactory }
    )

    private val navController by lazy {
        findNavController()
    }

    private val binding by viewBinding(
        FragmentSetNameWalletBinding::bind
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_set_name_wallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (binding.walletNameEdit.requestFocus()) {
            (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .toggleSoftInput(
                    0, InputMethodManager.HIDE_IMPLICIT_ONLY
                )
        }
        binding.walletNameEdit.doOnTextChanged { text, start, before, count ->
            binding.setNameButton.isEnabled = (text != "")
        }
        binding.setNameButton.setOnClickListener {
            val name = binding.walletNameEdit.text.toString()
            createViewModel.updateName(name)
                .observe(viewLifecycleOwner) {
                    Timber.d("Successfully updated name $name")
                    navController.navigate(R.id.walletEditingFragment)
                }
        }

        binding.toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }
    }
}
