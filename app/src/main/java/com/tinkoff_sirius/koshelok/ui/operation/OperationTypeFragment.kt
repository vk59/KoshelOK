package com.tinkoff_sirius.koshelok.ui.operation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.databinding.FragmentOperationTypeBinding

class OperationTypeFragment : Fragment() {

    private val binding: FragmentOperationTypeBinding by viewBinding(FragmentOperationTypeBinding::bind)

    private lateinit var viewModel: OperationTypeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_operation_type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners(view)
    }

    private fun initListeners(v: View){
        binding.setType.setOnClickListener{
//            v.findNavController().navigate(R.id.)
        }
        binding.toolbar.setNavigationOnClickListener {
            v.findNavController().navigate(R.id.action_operationTypeFragment_to_setSumFragment)
        }
    }
}
