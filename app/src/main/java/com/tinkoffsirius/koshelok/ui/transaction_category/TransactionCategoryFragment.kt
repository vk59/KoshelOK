package com.tinkoffsirius.koshelok.ui.transaction_category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.config.AppConfig
import com.tinkoffsirius.koshelok.databinding.FragmentTransactionCategoryBinding
import com.tinkoffsirius.koshelok.ui.transaction_category.adapters.TransactionCategoryAdapter

class TransactionCategoryFragment : Fragment() {
    private lateinit var viewModel: TransactionCategoryViewModel

    // nav controller lazy

    private val binding by viewBinding(FragmentTransactionCategoryBinding::bind)

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transaction_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerAdapter = TransactionCategoryAdapter()

        recyclerView = binding.transactionCategoryRecycler
        recyclerView.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(this@TransactionCategoryFragment.context)
        }

        val mTransaction = AppConfig.transactionExample

        recyclerAdapter.setData(mTransaction)

        initListeners(view)
    }

    private fun initListeners(v: View){
        binding.setCategory.setOnClickListener{
            findNavController().navigate(R.id.action_transactionCategoryFragment_to_transactionEditingFragment)
        }

        binding.toolbar.setNavigationOnClickListener {
            v.findNavController().navigate(R.id.action_transactionCategoryFragment_to_operationTypeFragment)
        }
    }
}
