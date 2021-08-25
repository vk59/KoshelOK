package com.tinkoffsirius.koshelok.ui.newcategory

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.appComponent
import com.tinkoffsirius.koshelok.databinding.FragmentCategoryNameBinding
import com.tinkoffsirius.koshelok.di.ViewModelFactory
import javax.inject.Inject

class CategoryNameFragment : Fragment() {

    private val binding: FragmentCategoryNameBinding by viewBinding(FragmentCategoryNameBinding::bind)

    private val navController by lazy { findNavController() }

    @Inject
    lateinit var newCategoryViewModelFactory: ViewModelFactory

    private val viewModel: NewCategoryViewModel by viewModels(
        factoryProducer = {
            newCategoryViewModelFactory
        }
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_name, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.categoryNameText.requestFocus()

        if (binding.categoryNameText.requestFocus()) {
            (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
                0, InputMethodManager.HIDE_IMPLICIT_ONLY
            )
        }
        initListeners(view)
    }

    private fun initListeners(v: View) {

        binding.setNewName.setOnClickListener {
            viewModel.updateNewCategoryName(binding.categoryNameText.text.toString())
                .observe(viewLifecycleOwner, {})
            navController.popBackStack()
        }

        binding.toolbar.setNavigationOnClickListener {
            viewModel.updateNewCategoryName(binding.categoryNameText.text.toString())
                .observe(viewLifecycleOwner, {})
            navController.popBackStack()
        }
    }
}
