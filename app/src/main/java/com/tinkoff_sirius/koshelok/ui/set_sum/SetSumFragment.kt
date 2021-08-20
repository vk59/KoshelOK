package com.tinkoff_sirius.koshelok.ui.set_sum

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.databinding.FragmentSetSumBinding

class SetSumFragment : Fragment() {
    private val binding: FragmentSetSumBinding by viewBinding(FragmentSetSumBinding::bind)

    var pref: SharedPreferences? = null

    private lateinit var viewModel: SetSumViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_set_sum, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = this.activity?.getSharedPreferences("CREATED_TRANSACTION", Context.MODE_PRIVATE)
        binding.sumText.setText(pref?.getString("sum", ""))
        initListeners(view)
    }

    private fun initListeners(v: View) {
        binding.setSumButton.setOnClickListener {
            if (!binding.sumText.text?.trim().isNullOrEmpty() && !binding.sumText.text?.toString().equals(".")) {
                saveData(binding.sumText.text.toString())
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

    fun saveData(sum: String){
        val editor = pref?.edit()
        editor?.putString("sum", sum)
        editor?.apply()
    }
}
