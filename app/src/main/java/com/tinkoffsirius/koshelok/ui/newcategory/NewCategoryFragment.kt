package com.tinkoffsirius.koshelok.ui.newcategory

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.appComponent
import com.tinkoffsirius.koshelok.databinding.FragmentNewCategoryBinding
import com.tinkoffsirius.koshelok.di.ViewModelFactory
import com.tinkoffsirius.koshelok.entities.TransactionType
import com.tinkoffsirius.koshelok.ui.newcategory.adapters.NewCategoriesAdapter
import dev.sasikanth.colorsheet.ColorSheet
import javax.inject.Inject

class NewCategoryFragment : Fragment() {

    private val binding: FragmentNewCategoryBinding by viewBinding(FragmentNewCategoryBinding::bind)

    @Inject
    lateinit var newCategoryViewModelFactory: ViewModelFactory

    private val viewModel: NewCategoryViewModel by viewModels(
        factoryProducer = {
            newCategoryViewModelFactory
        })

    private val navController by lazy { findNavController() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.newCategory.observe(viewLifecycleOwner, {
            binding.transNameLabel.buttonText.text = it.categoryName
            binding.transTypeLabel.buttonText.text =
                when (it.typeName.name) {
                    TransactionType.INCOME.name -> "Доход"
                    TransactionType.OUTCOME.name -> "Расход"
                    else -> "Выберите тип"
                }
        })

        val recyclerAdapter = NewCategoriesAdapter{
            viewModel.updateNewCategoryIcon(iconId = it.imgId)
                .observe(viewLifecycleOwner, {

                })
        }

        binding.categoryRecycleView.apply {
            adapter = recyclerAdapter
            layoutManager = GridLayoutManager(this@NewCategoryFragment.context, 6)
        }



        binding.transCategoryIconLabel.setOnClickListener {
            ColorSheet().colorPicker(
                colors = intArrayOf(
                    requireContext().getColor(R.color.red),
                    requireContext().getColor(R.color.green),
                    requireContext().getColor(R.color.main_blue)
                ),
                selectedColor = Color.RED,
                listener = { color ->
                    //Log.d("TAG" , color.toString())
                    viewModel.updateNewCategoryColor(color)
                })
                .show(requireActivity().supportFragmentManager)
        }

        viewModel.icons.observe(viewLifecycleOwner) {
            recyclerAdapter.setData(it)
        }
        initListeners(view)
    }


    private fun initListeners(v: View) {

        binding.createTransactionButton.setOnClickListener {}

        binding.toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }

        binding.transNameLabel.setOnClickListener {
            navController.navigate(R.id.action_newCategoryFragment_to_categoryNameFragment2)
        }

        binding.transTypeLabel.setOnClickListener {
            navController.navigate(R.id.action_newCategoryFragment_to_newTypeFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateState()
    }
}
