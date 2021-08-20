package com.tinkoff_sirius.koshelok.ui.transaction_editing

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.databinding.FragmentTransactionEditingBinding
import com.tinkoff_sirius.koshelok.repository.PosedTransactionSharedRepository
import com.tinkoff_sirius.koshelok.repository.SharedPreferencesFactory
import com.tinkoff_sirius.koshelok.ui.main.MainViewModel


class TransactionEditingFragment : Fragment() {

    private val viewModel: TransactionEditingViewModel by viewModels(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return TransactionEditingViewModel(
                    PosedTransactionSharedRepository(
                        SharedPreferencesFactory().create(
                            requireContext(),
                            SharedPreferencesFactory.TRANSACTION_DATA
                        )
                    )
                ) as T
            }
        }
    })

    private val binding by viewBinding(FragmentTransactionEditingBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transaction_editing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.transaction.observe(viewLifecycleOwner, {
            //работа с полями класса
            binding.transEditingSumLabel.buttonText.text = it.type

        })


//        val sum = getData(CreatedTransactionShared.SUMMARY)
//
//        val type = getData(CreatedTransactionShared.TYPE)

        //val category = getData(CreatedTransactionShared.CATEGORY)

        // binding.transEditingSumLabel.buttonText.text = sum
        ///binding.transEditingCategoryLabel.buttonText.text = category
        //binding.transEditingTypeLabel.buttonText.text = type

        initListeners(view)
    }

    private fun initListeners(v: View) {


        binding.toolbar.setNavigationOnClickListener {
            v.findNavController()
                .navigate(R.id.action_transactionEditingFragment_to_transactionCategoryFragment)
        }

    }

//    private fun getData(type: String): String {
//        return CreatedTransactionShared(
//            SharedPreferencesFactory().create(
//                requireContext(),
//                SharedPreferencesFactory.TRANSACTION_DATA
//            )
//        ).getTransaction(type)
//    }


}