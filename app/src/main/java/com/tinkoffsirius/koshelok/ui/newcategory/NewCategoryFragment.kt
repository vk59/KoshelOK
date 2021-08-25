package com.tinkoffsirius.koshelok.ui.newcategory

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.Icons
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.appComponent
import com.tinkoffsirius.koshelok.databinding.FragmentNewCategoryBinding
import com.tinkoffsirius.koshelok.databinding.ItemNewCategoryBinding
import com.tinkoffsirius.koshelok.di.ViewModelFactory
import com.tinkoffsirius.koshelok.entities.Icon
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

        val recyclerAdapter = NewCategoriesAdapter()

        binding.categoryRecycleView.apply {
            adapter = recyclerAdapter
            layoutManager = GridLayoutManager(this@NewCategoryFragment.context, 6)
        }

        val list = Icons.values().toList().map { Icon(it.drawableId, R.color.main_blue) }

        recyclerAdapter.setData(list)

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

        binding.transCategoryIconLabel.setOnClickListener {
//            ColorSheet().colorPicker(
//                colors = intArrayOf(R.color.black , R.color.main_blue),
//                listener = { color ->
//                    // Handle color
//                })
//                .show(requireFragmentManager())

        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateState()
    }
}
