package com.tinkoffsirius.koshelok.ui.createtransaction.operation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.appComponent
import com.tinkoffsirius.koshelok.databinding.FragmentOperationTypeBinding
import com.tinkoffsirius.koshelok.di.modules.ViewModelFactory
import com.tinkoffsirius.koshelok.entities.TransactionType
import com.tinkoffsirius.koshelok.ui.createtransaction.TransactionEditingViewModel
import javax.inject.Inject

class OperationTypeFragment : Fragment() {

    private val binding: FragmentOperationTypeBinding by viewBinding(FragmentOperationTypeBinding::bind)

    @Inject
    lateinit var transactionViewModelFactory: ViewModelFactory

    private val viewModel: TransactionEditingViewModel by viewModels(
        factoryProducer = {
            transactionViewModelFactory
        })

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_operation_type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.transaction.observe(viewLifecycleOwner, {
            when (it.type) {
                TransactionType.INCOME.name -> binding.operationTypeRadioButtonIncome.isChecked =
                    true
                TransactionType.OUTCOME.name -> binding.operationTypeRadioButtonExpense.isChecked =
                    true
                else -> {
                    binding.setType.isEnabled = false
                }
            }
        })

        initListeners(view)
    }

    private fun initListeners(v: View) {

        binding.operationTypeRadioButtonIncome.setOnClickListener {
            binding.setType.isClickable = false
            viewModel.updateTransactionType(TransactionType.INCOME.name)
                .observe(viewLifecycleOwner) {
                    binding.setType.isEnabled = true
                    binding.setType.isClickable = true
                }
        }

        binding.operationTypeRadioButtonExpense.setOnClickListener {
            binding.setType.isClickable = false
            viewModel.updateTransactionType(TransactionType.OUTCOME.name)
                .observe(viewLifecycleOwner) {
                    binding.setType.isEnabled = true
                    binding.setType.isClickable = true
                }
        }

        binding.setType.setOnClickListener {

            if (binding.operationTypeRadioButtonIncome.isChecked || binding.operationTypeRadioButtonExpense.isChecked) {

                v.findNavController()
                    .navigate(R.id.action_operationTypeFragment_to_transactionCategoryFragment)
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}
