package com.tinkoffsirius.koshelok.ui.createtransaction.transactioncategory

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.appComponent
import com.tinkoffsirius.koshelok.databinding.FragmentTransactionCategoryBinding
import com.tinkoffsirius.koshelok.di.modules.ViewModelFactory
import com.tinkoffsirius.koshelok.entities.TransactionType
import com.tinkoffsirius.koshelok.ui.createtransaction.TransactionEditingViewModel
import com.tinkoffsirius.koshelok.ui.createtransaction.transactioncategory.adapters.TransactionCategoryAdapter
import com.tinkoffsirius.koshelok.ui.newcategory.NewCategoryViewModel
import javax.inject.Inject

class TransactionCategoryFragment : Fragment() {

    @Inject
    lateinit var transactionViewModelFactory: ViewModelFactory

    private val viewModel: TransactionEditingViewModel by viewModels(
        factoryProducer = {
            transactionViewModelFactory
        })

    @Inject
    lateinit var newCategoryViewModelFactory: ViewModelFactory

    private val newCategoryViewModel: NewCategoryViewModel by viewModels(
        factoryProducer = {
            newCategoryViewModelFactory
        })

    private val binding by viewBinding(FragmentTransactionCategoryBinding::bind)

    private val navController by lazy { findNavController() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

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

        viewModel.transaction.observe(viewLifecycleOwner, { posedTransaction ->
            viewModel.getCategories(TransactionType.valueOf(posedTransaction.type))
        })

        viewModel.categories.observe(viewLifecycleOwner) { list ->
            recyclerAdapter.setData(list)
        }

        initListeners(view)
    }

    private fun initListeners(v: View) {

        binding.setCategory.setOnClickListener {
            navController
                .navigate(R.id.action_transactionCategoryFragment_to_transactionEditingFragment)
        }

        binding.toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }

        binding.transCategoryCreate.setOnClickListener {
            newCategoryViewModel.removeTransaction()
            navController
                .navigate(R.id.action_transactionCategoryFragment_to_newCategoryFragment)
        }

    }
}
