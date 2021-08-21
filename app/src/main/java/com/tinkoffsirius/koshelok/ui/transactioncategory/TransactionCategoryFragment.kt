package com.tinkoffsirius.koshelok.ui.transactioncategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.config.AppConfig
import com.tinkoffsirius.koshelok.databinding.FragmentTransactionCategoryBinding
import com.tinkoffsirius.koshelok.repository.PosedTransactionSharedRepository
import com.tinkoffsirius.koshelok.repository.SharedPreferencesFactory
import com.tinkoffsirius.koshelok.ui.transactioncategory.adapters.TransactionCategoryAdapter
import com.tinkoffsirius.koshelok.ui.transactionediting.TransactionEditingViewModel

class TransactionCategoryFragment : Fragment() {

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


    private val binding by viewBinding(FragmentTransactionCategoryBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transaction_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.transaction.observe(viewLifecycleOwner, {
            //Выделить сохраненый Item

        })

        binding.setCategory.isEnabled = false

        val recyclerAdapter = TransactionCategoryAdapter {
            viewModel.updateTransactionCategory(category = it)
                .observe(viewLifecycleOwner, {
                    binding.setCategory.isEnabled = true
                })
        }

        binding.transactionCategoryRecycler.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(this@TransactionCategoryFragment.context)
        }

        val category = AppConfig.transactionExample.map { it.category }

        recyclerAdapter.setData(category)

        initListeners(view)
    }

    private fun initListeners(v: View) {

        binding.setCategory.setOnClickListener {
            // TODO
            v.findNavController()
                .navigate(R.id.action_transactionCategoryFragment_to_transactionEditingFragment)
        }

        binding.toolbar.setNavigationOnClickListener {
            v.findNavController()
                .navigate(R.id.action_transactionCategoryFragment_to_operationTypeFragment)
        }
    }

}
