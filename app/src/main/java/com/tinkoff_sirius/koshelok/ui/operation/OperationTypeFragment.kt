package com.tinkoff_sirius.koshelok.ui.operation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.databinding.FragmentOperationTypeBinding
import com.tinkoff_sirius.koshelok.repository.PosedTransactionSharedRepository
import com.tinkoff_sirius.koshelok.repository.SharedPreferencesFactory
import com.tinkoff_sirius.koshelok.ui.transaction_editing.TransactionEditingViewModel

class OperationTypeFragment : Fragment() {

    private val binding: FragmentOperationTypeBinding by viewBinding(FragmentOperationTypeBinding::bind)

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_operation_type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.transaction.observe(viewLifecycleOwner, {
            if (it.type == "Доход") binding.operationTypeRadioButtonIncome.isChecked =
                true else if (it.type == "Расход") binding.operationTypeRadioButtonExpense.isChecked =
                true
        })

        initListeners(view)
    }

    private fun initListeners(v: View) {

        binding.operationTypeRadioButtonIncome.setOnClickListener {

            binding.setType.isClickable = false
            viewModel.updateTransactionType("Доход").observe(viewLifecycleOwner) {
                binding.setType.isClickable = true
            }

        }

        binding.operationTypeRadioButtonExpense.setOnClickListener {
            binding.setType.isClickable = false
            viewModel.updateTransactionType("Расход").observe(viewLifecycleOwner) {
                binding.setType.isClickable = true
            }
        }

        binding.setType.setOnClickListener {
            if (binding.operationTypeRadioButtonIncome.isChecked || binding.operationTypeRadioButtonExpense.isChecked) {
                v.findNavController()
                    .navigate(R.id.action_operationTypeFragment_to_transactionCategoryFragment)
            } else {
                Toast.makeText(requireContext(), "Выберите категорию!", Toast.LENGTH_LONG).show()
            }
        }


        binding.toolbar.setNavigationOnClickListener {
            v.findNavController().navigate(R.id.action_operationTypeFragment_to_setSumFragment)
        }
    }

}