package com.tinkoff_sirius.koshelok.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.databinding.FragmentSetSumBinding


class SetSumFragment : Fragment() {
    private val binding: FragmentSetSumBinding by viewBinding(FragmentSetSumBinding::bind)

    private lateinit var viewModel: SetSumViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_set_sum, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners(view)
    }



    private fun initListeners(v: View){

        binding.setSumButton.setOnClickListener{
            if(!binding.sumText.text?.trim().isNullOrEmpty()) {
                v.findNavController().navigate(R.id.action_setSumFragment_to_operationTypeFragment)
            }else{
                Toast.makeText(requireContext() , "Введите сумму!" , Toast.LENGTH_LONG).show()
            }
        }
        binding.toolbar.setNavigationOnClickListener {
            v.findNavController().navigate(R.id.action_setSumFragment_to_mainFragment)
        }

    }
}