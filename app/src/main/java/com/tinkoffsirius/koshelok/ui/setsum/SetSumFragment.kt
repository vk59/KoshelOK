package com.tinkoffsirius.koshelok.ui.setsum

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.Dependencies
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.databinding.FragmentSetSumBinding
import com.tinkoffsirius.koshelok.ui.transactionediting.TransactionEditingViewModel
import androidx.core.content.ContextCompat.getSystemService




class SetSumFragment : Fragment() {
    private val binding: FragmentSetSumBinding by viewBinding(FragmentSetSumBinding::bind)

    private val viewModel: TransactionEditingViewModel by viewModels(
        factoryProducer = {
            Dependencies.transactionViewModelFactory
        })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_set_sum, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sumText.requestFocus()

        if (binding.sumText.requestFocus()) {
            (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
                0, InputMethodManager.HIDE_IMPLICIT_ONLY
            )
        }

        viewModel.transaction.observe(viewLifecycleOwner, {
            //TODO кидает ошибку если файл не создан
            binding.sumText.setText(it.sum)
        })

        initListeners(view)
    }

    private fun initListeners(v: View) {
        binding.setSumButton.setOnClickListener {

            if (!binding.sumText.text?.trim().isNullOrEmpty() && !binding.sumText.text?.toString()
                    .equals(".")
            ) {

                viewModel.updateTransactionSum(binding.sumText.text.toString()).observe(viewLifecycleOwner, {})

                v.findNavController()
                    .navigate(R.id.action_setSumFragment_to_operationTypeFragment)
            } else {
                Toast.makeText(requireContext(), "Введите сумму!", Toast.LENGTH_LONG).show()
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            v.findNavController().navigate(R.id.action_setSumFragment_to_mainFragment)
        }
    }
}
