package com.tinkoffsirius.koshelok.ui.newcategory

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.databinding.FragmentTransactionCategoryBinding
import com.tinkoffsirius.koshelok.databinding.NewTypeFragmentBinding

class NewTypeFragment : Fragment() {

    private lateinit var viewModel: NewTypeViewModel

    private val binding by viewBinding(NewTypeFragmentBinding::bind)

    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_type_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners(view)
    }

    private fun initListeners(v: View) {

        val bundle = Bundle()

        binding.setType.setOnClickListener {
            bundle.putString(
                "NEW_TYPE",
                if (binding.operationTypeRadioButtonIncome.isChecked) "Доход" else "Расход"
            )
            navController
                .navigate(R.id.action_newTypeFragment_to_newCategoryFragment, bundle)
        }

        binding.toolbar.setOnClickListener {
            bundle.putString(
                "NEW_TYPE",
                if (binding.operationTypeRadioButtonIncome.isChecked) "Доход" else "Расход"
            )
            navController
                .navigate(R.id.action_newTypeFragment_to_newCategoryFragment, bundle)
        }
    }


}