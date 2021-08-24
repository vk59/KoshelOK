package com.tinkoffsirius.koshelok.ui.newcategory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.config.AppConfig
import com.tinkoffsirius.koshelok.databinding.FragmentNewCategoryBinding
import com.tinkoffsirius.koshelok.databinding.FragmentSetSumBinding
import com.tinkoffsirius.koshelok.ui.newcategory.adapters.NewCategorysAdapter

class NewCategoryFragment : Fragment() {

    private val binding: FragmentNewCategoryBinding by viewBinding(FragmentNewCategoryBinding::bind)

    private lateinit var viewModel: NewCategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerAdapter = NewCategorysAdapter()

        binding.categoryRecycleView.apply {
            adapter = recyclerAdapter
            layoutManager = GridLayoutManager(this@NewCategoryFragment.context, 6 )
        }

        val list = AppConfig.transactionExample

        val newNameText = arguments?.getString("NEW_NAME")
        val newTypeText = arguments?.getString("NEW_TYPE")

        binding.transNameLabel.buttonText.text = newNameText
        binding.transTypeLabel.buttonText.text = newTypeText

        //if(newNameText == null) binding.transNameLabel.buttonText.text = "Новая категория"
        //if(newTypeText == null) binding.transTypeLabel.buttonText.text = "Доход"

        recyclerAdapter.setData(list)

        initListeners(view)
    }

    private fun initListeners(v: View) {

        binding.createTransactionButton.setOnClickListener {


            //v.findNavController().navigate(R.id.action_categoryNameFragment_to_newCategoryFragment)
        }

        binding.transNameLabel.setOnClickListener {
            findNavController().navigate(R.id.action_newCategoryFragment_to_categoryNameFragment2)
        }

        binding.transTypeLabel.setOnClickListener {
            findNavController().navigate(R.id.action_newCategoryFragment_to_newTypeFragment)
        }

    }

}