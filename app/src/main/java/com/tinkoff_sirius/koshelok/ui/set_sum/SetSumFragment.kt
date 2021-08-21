package com.tinkoff_sirius.koshelok.ui.set_sum

import android.os.Bundle
import android.os.FileUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.databinding.FragmentSetSumBinding
import com.tinkoff_sirius.koshelok.repository.PosedTransactionSharedRepository
import com.tinkoff_sirius.koshelok.repository.SharedPreferencesFactory
import com.tinkoff_sirius.koshelok.ui.transaction_editing.TransactionEditingViewModel

class SetSumFragment : Fragment() {
    private val binding: FragmentSetSumBinding by viewBinding(FragmentSetSumBinding::bind)

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_set_sum, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.transaction.observe(viewLifecycleOwner, {
            //TODO кидает ошибку если файл не создан
            binding.sumText.setText(it.sum)
        })

        initListeners(view)
    }

    private fun initListeners(v: View) {
        binding.setSumButton.setOnClickListener {


            if (!binding.sumText.text?.trim().isNullOrEmpty() && !binding.sumText.text?.toString()
                    .equals(".")
            ) {

                viewModel.updateTransactionSum(binding.sumText.text.toString()).observe(viewLifecycleOwner, {})

                v.findNavController()
                    .navigate(R.id.action_setSumFragment_to_operationTypeFragment)

            } else {
                Toast.makeText(requireContext(), "Введите сумму!", Toast.LENGTH_LONG).show()
            }
        }

        binding.toolbar.setNavigationOnClickListener {

            v.findNavController().navigate(R.id.action_setSumFragment_to_mainFragment)

        }

    }


}
