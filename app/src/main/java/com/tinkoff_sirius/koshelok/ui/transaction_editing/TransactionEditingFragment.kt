package com.tinkoff_sirius.koshelok.ui.transaction_editing

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.databinding.FragmentTransactionEditingBinding
import com.tinkoff_sirius.koshelok.repository.PosedTransactionSharedRepository
import com.tinkoff_sirius.koshelok.repository.SharedPreferencesFactory
import com.tinkoff_sirius.koshelok.ui.main.MainViewModel
import java.util.*


class TransactionEditingFragment : Fragment() {

    private val viewModel: TransactionEditingViewModel by viewModels(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return TransactionEditingViewModel(
                    PosedTransactionSharedRepository(
                        SharedPreferencesFactory().create(
                            requireContext(),
                            SharedPreferencesFactory.TRANSACTION_DATA
                        )
                    )
                ) as T
            }
        }
    })

    private val binding by viewBinding(FragmentTransactionEditingBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transaction_editing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.transaction.observe(viewLifecycleOwner, {
            binding.transEditingSumLabel.buttonText.text = it.sum
            binding.transEditingTypeLabel.buttonText.text = it.type
            binding.transEditingCategoryLabel.buttonText.text = it.category
        })

        initListeners(view)
    }

    private fun initListeners(v: View) {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        binding.transEditingDateLabel.buttonText.text = "$day.$month"

        binding.transEditingDateLabel.setOnClickListener {

            val dpd = DatePickerDialog(requireContext(), { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                binding.transEditingDateLabel.buttonText.setText("$dayOfMonth $month")
            }, year, month, day)
            dpd.show()

        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController()
                .navigate(R.id.action_transactionEditingFragment_to_transactionCategoryFragment)
        }

        binding.transEditingSumLabel.setOnClickListener {
            findNavController()
                .navigate(R.id.action_transactionEditingFragment_to_setSumFragment)
        }

        binding.transEditingTypeLabel.setOnClickListener {
            findNavController()
                .navigate(R.id.action_transactionEditingFragment_to_operationTypeFragment)
        }

        binding.transEditingCategoryLabel.setOnClickListener {
            findNavController()
                .navigate(R.id.action_transactionEditingFragment_to_transactionCategoryFragment)
        }

        binding.createTransactionButton.setOnClickListener{
            findNavController()
                .navigate(R.id.action_transactionEditingFragment_to_mainFragment)
        }

    }


}