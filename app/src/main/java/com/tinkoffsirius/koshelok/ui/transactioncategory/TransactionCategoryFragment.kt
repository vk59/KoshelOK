package com.tinkoffsirius.koshelok.ui.transactioncategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.Dependencies
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.config.AppConfig
import com.tinkoffsirius.koshelok.databinding.FragmentTransactionCategoryBinding
import com.tinkoffsirius.koshelok.entities.Category
import com.tinkoffsirius.koshelok.entities.Transaction
import com.tinkoffsirius.koshelok.entities.TransactionType
import com.tinkoffsirius.koshelok.ui.transactioncategory.adapters.TransactionCategoryAdapter
import com.tinkoffsirius.koshelok.ui.transactionediting.TransactionEditingViewModel

class TransactionCategoryFragment : Fragment() {

    private val viewModel: TransactionEditingViewModel by viewModels(
        factoryProducer = {
            Dependencies.transactionViewModelFactory
        })

    private val binding by viewBinding(FragmentTransactionCategoryBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transaction_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setCategory.isEnabled = false

        val recyclerAdapter = TransactionCategoryAdapter {
            viewModel.updateTransactionCategory(category = it)
                .observe(viewLifecycleOwner, {
                    binding.setCategory.isEnabled = true
                })
        }

        viewModel.transaction.observe(viewLifecycleOwner, {
//            recyclerAdapter.setPosition((it.category.id?.toInt()?.minus(1)))
//            binding.setCategory.isEnabled = true

        })

        binding.transactionCategoryRecycler.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(this@TransactionCategoryFragment.context)
        }

        viewModel.transaction.observe(viewLifecycleOwner, {
            val list: List<Category>?
            if (it.type == TransactionType.INCOME.name) {
                list =
                    AppConfig.transactionExample.filter { it.typeName.name == TransactionType.INCOME.name }
                recyclerAdapter.setData(list)
            } else {
                list =
                    AppConfig.transactionExample.filter { it.typeName.name == TransactionType.OUTCOME.name }
            }
            recyclerAdapter.setData(list)

        })


        //recyclerAdapter.setData(list!!)

        initListeners(view)
    }

    private fun initListeners(v: View) {

        binding.setCategory.setOnClickListener {
            // TODO
            v.findNavController()
                .navigate(R.id.action_transactionCategoryFragment_to_transactionEditingFragment)
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}
