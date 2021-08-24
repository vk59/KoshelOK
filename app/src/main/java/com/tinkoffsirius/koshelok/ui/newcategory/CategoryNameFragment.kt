package com.tinkoffsirius.koshelok.ui.newcategory

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.databinding.FragmentCategoryNameBinding
import com.tinkoffsirius.koshelok.databinding.FragmentSetSumBinding

class CategoryNameFragment : Fragment() {
    private val binding: FragmentCategoryNameBinding by viewBinding(FragmentCategoryNameBinding::bind)

    private lateinit var viewModel: CategoryNameViewModel

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

        val bundle = Bundle()

        binding.setNewName.setOnClickListener {
            bundle.putString("NEW_NAME" , binding.categoryNameText.text.toString())
            findNavController().navigate(R.id.action_categoryNameFragment_to_newCategoryFragment , bundle)
        }

        binding.toolbar.setNavigationOnClickListener {
            bundle.putString("NEW_NAME" , binding.categoryNameText.text.toString())
            findNavController().navigate(R.id.action_categoryNameFragment_to_newCategoryFragment , bundle)
        }
    }


}
