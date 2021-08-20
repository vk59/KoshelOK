package com.tinkoff_sirius.koshelok.ui.operation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.databinding.FragmentOperationTypeBinding

class OperationTypeFragment : Fragment() {

    private val binding: FragmentOperationTypeBinding by viewBinding(FragmentOperationTypeBinding::bind)
    private val navController = findNavController()

    private val viewModel: OperationTypeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_operation_type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners() {
        binding.setType.setOnClickListener {
            navController.navigate(R.id.action_operationTypeFragment_to_transactionCategoryFragment)
        }

        binding.toolbar.setNavigationOnClickListener {
            navController.navigate(R.id.action_operationTypeFragment_to_setSumFragment)
        }
    }
}
