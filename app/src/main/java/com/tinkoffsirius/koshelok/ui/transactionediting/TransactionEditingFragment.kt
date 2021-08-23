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
import com.google.android.material.snackbar.Snackbar
import com.tinkoffsirius.koshelok.Dependencies.transactionViewModelFactory
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.databinding.FragmentTransactionEditingBinding
import com.tinkoffsirius.koshelok.entities.TransactionType
import com.tinkoffsirius.koshelok.ui.DateUtils
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import java.text.SimpleDateFormat
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
            binding.transEditingTypeLabel.buttonText.text =
                if (it.type == TransactionType.INCOME.name) "Доход" else "Расход"
            binding.transEditingCategoryLabel.buttonText.text = it.category.categoryName
        })

        initListeners(view)
    }

    private fun initListeners(v: View) {

        val c = Calendar.getInstance()
        val dateTransaction: LocalDateTime = viewModel.transactionDateTime.value!!
        var year = dateTransaction.year
        var month = dateTransaction.monthNumber
        var day = dateTransaction.dayOfMonth
        var hour = dateTransaction.hour
        var minute = dateTransaction.minute
        //val time = "${dateTransaction.hour}:${dateTransaction.minute}"
        val localDate = LocalDate(year, month, day)
        //val timeZone = TimeZone.getDefault().id

        binding.transEditingDateLabel.buttonText.text =
            DateUtils.toUIString(localDate, requireContext())
        binding.transEditingTimeLabel.buttonText.text = "$hour:$minute"

        binding.transEditingDateLabel.setOnClickListener {

            val dpd = DatePickerDialog(requireContext(), { view, dYear, dMonthOfYear, dDayOfMonth ->
                binding.transEditingDateLabel.buttonText.text =
                    DateUtils.toUIString(
                        LocalDate(dYear, dMonthOfYear + 1, dDayOfMonth),
                        requireContext()
                    )
                year = dYear
                month = dMonthOfYear + 1
                day = dDayOfMonth
            }, year, month - 1, day)
            dpd.show()

        }

        binding.transEditingTimeLabel.setOnClickListener {

            Instant.fromEpochMilliseconds(c.timeInMillis)
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, pHour, pMinute ->
                c.set(Calendar.HOUR_OF_DAY, pHour)
                c.set(Calendar.MINUTE, pMinute)
                binding.transEditingTimeLabel.buttonText.text =
                    SimpleDateFormat("HH:mm").format(c.time)
                hour = pHour
                minute = pMinute
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
            findNavController().popBackStack()
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
            viewModel.updateDate(LocalDateTime(year, month, day, hour, minute, 0, 0))
            viewModel.saveTransaction().observe(viewLifecycleOwner) {
                Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT).show()
            }
            findNavController()
                .navigate(R.id.action_transactionEditingFragment_to_mainFragment)
        }
    }
}
