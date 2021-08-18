package com.tinkoff_sirius.koshelok.ui.transaction_category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.config.AppConfig
import com.tinkoff_sirius.koshelok.databinding.TransactionCategoryFragmentBinding
import com.tinkoff_sirius.koshelok.ui.transaction_category.adapters.TransactionCategoryAdapter

class TransactionCategoryFragment : Fragment() {
    private lateinit var viewModel: TransactionCategoryViewModel

    private val binding by viewBinding(TransactionCategoryFragmentBinding::bind)

    private val recyclerView: RecyclerView by lazy(LazyThreadSafetyMode.NONE) {
        binding.transactionCategoryRecycler
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.transaction_category_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerAdapter = TransactionCategoryAdapter()

        recyclerView.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(this@TransactionCategoryFragment.context)
        }

        val mTransaction = AppConfig.transactionExample

        recyclerAdapter.setData(mTransaction)
    }
}
