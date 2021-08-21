package com.tinkoff_sirius.koshelok.ui.transaction_category

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
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.config.AppConfig
import com.tinkoff_sirius.koshelok.databinding.FragmentTransactionCategoryBinding
import com.tinkoff_sirius.koshelok.repository.PosedTransactionSharedRepository
import com.tinkoff_sirius.koshelok.repository.SharedPreferencesFactory
import com.tinkoff_sirius.koshelok.ui.transaction_category.adapters.TransactionCategoryAdapter
import com.tinkoff_sirius.koshelok.ui.transaction_editing.TransactionEditingViewModel

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

    private val recyclerView: RecyclerView by lazy(LazyThreadSafetyMode.NONE) {
        binding.transactionCategoryRecycler
    }

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

        val recyclerAdapter = TransactionCategoryAdapter {
            binding.setCategory.isClickable = false
            viewModel.updateTransactionCategory(category = it)
                .observe(viewLifecycleOwner, {
                    binding.setCategory.isClickable = true
                })
        }

        recyclerView.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(this@TransactionCategoryFragment.context)
        }

        val category = AppConfig.transactionExample.map { it.category }

        recyclerAdapter.setData(category)

        initListeners(view)
    }

    private fun initListeners(v: View) {

        binding.setCategory.setOnClickListener {

            v.findNavController()
                .navigate(R.id.action_transactionCategoryFragment_to_transactionEditingFragment)
        }

        binding.toolbar.setNavigationOnClickListener {
            v.findNavController()
                .navigate(R.id.action_transactionCategoryFragment_to_operationTypeFragment)
        }
    }

}
