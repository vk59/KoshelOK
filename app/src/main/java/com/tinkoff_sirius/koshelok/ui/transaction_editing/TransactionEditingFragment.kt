package com.tinkoff_sirius.koshelok.ui.transaction_editing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.databinding.FragmentTransactionEditingBinding

class TransactionEditingFragment : Fragment() {

    private lateinit var viewModel: TransactionEditingViewModel

    private val binding by viewBinding(FragmentTransactionEditingBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transaction_editing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners(view)
    }

    private fun initListeners(v: View){
        binding.toolbar.setNavigationOnClickListener {
            v.findNavController().navigate(R.id.action_transactionEditingFragment_to_transactionCategoryFragment)
        }
    }
}
