package com.tinkoffsirius.koshelok.ui.transactionediting

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.Dependencies.transactionViewModelFactory
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.databinding.FragmentTransactionEditingBinding
import com.tinkoffsirius.koshelok.ui.DateUtils
import kotlinx.datetime.LocalDate
import java.text.SimpleDateFormat
import timber.log.Timber
import java.util.*

class TransactionEditingFragment : Fragment() {

    private val viewModel: TransactionEditingViewModel by viewModels(
        factoryProducer = {
            transactionViewModelFactory
        })

    private val binding by viewBinding(FragmentTransactionEditingBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transaction_editing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.transaction.observe(viewLifecycleOwner, {
            binding.transEditingSumLabel.buttonText.text = it.sum
            binding.transEditingTypeLabel.buttonText.text = it.type
            binding.transEditingCategoryLabel.buttonText.text = it.category.categoryName
        })

        initListeners(view)
    }

    private fun initListeners(v: View) {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val time = "${c.get(Calendar.HOUR_OF_DAY)} : ${c.get(Calendar.MINUTE)}"

        binding.transEditingDateLabel.buttonText.text =
            DateUtils.toUIString(LocalDate(year, month, day), requireContext())

        binding.transEditingTimeLabel.buttonText.text = time


        binding.transEditingDateLabel.setOnClickListener {

            val dpd = DatePickerDialog(requireContext(), { view, year, monthOfYear, dayOfMonth ->
                binding.transEditingDateLabel.buttonText.text =
                    DateUtils.toUIString(LocalDate(year, monthOfYear, dayOfMonth), requireContext())
            }, year, month, day)
            dpd.show()
        }

        binding.transEditingTimeLabel.setOnClickListener {

            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                c.set(Calendar.HOUR_OF_DAY, hour)
                c.set(Calendar.MINUTE, minute)
                binding.transEditingTimeLabel.buttonText.text =
                    SimpleDateFormat("HH:mm").format(c.time)
            }
            TimePickerDialog(
                requireContext(),
                timeSetListener,
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),
                true
            ).show()
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController()
                .navigate(R.id.action_transactionEditingFragment_to_transactionCategoryFragment)
        }

        binding.transEditingSumLabel.setOnClickListener {
            findNavController()
                .navigate(R.id.action_transactionEditingFragment_to_setSumFragment)
        }

        binding.transEditingTypeLabel.setOnClickListener {
            findNavController()
                .navigate(R.id.action_transactionEditingFragment_to_operationTypeFragment)
        }

        binding.transEditingCategoryLabel.setOnClickListener {
            findNavController()
                .navigate(R.id.action_transactionEditingFragment_to_transactionCategoryFragment)
        }

        binding.createTransactionButton.setOnClickListener {
            viewModel.saveTransaction().observe(viewLifecycleOwner) {
                Timber.tag("tut").d(it.message)
            }
            findNavController()
                .navigate(R.id.action_transactionEditingFragment_to_mainFragment)
        }
    }
}
