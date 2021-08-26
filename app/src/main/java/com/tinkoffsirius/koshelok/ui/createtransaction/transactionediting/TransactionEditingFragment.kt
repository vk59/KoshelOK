package com.tinkoffsirius.koshelok.ui.createtransaction.transactionediting

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.appComponent
import com.tinkoffsirius.koshelok.databinding.FragmentTransactionEditingBinding
import com.tinkoffsirius.koshelok.di.modules.ViewModelFactory
import com.tinkoffsirius.koshelok.entities.TransactionType
import com.tinkoffsirius.koshelok.ui.createtransaction.TransactionEditingViewModel
import com.tinkoffsirius.koshelok.utils.DateUtils
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class TransactionEditingFragment : Fragment() {

    @Inject
    lateinit var transactionViewModelFactory: ViewModelFactory

    private val viewModel: TransactionEditingViewModel by viewModels(
        factoryProducer = {
            transactionViewModelFactory
        })

    private val binding by viewBinding(FragmentTransactionEditingBinding::bind)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

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
            binding.transSumLabel.buttonText.text = it.sum
            binding.transTypeLabel.buttonText.text =
                if (it.type == TransactionType.INCOME.name) "Доход" else "Расход"
            binding.transCategoryLabel.buttonText.text = it.category.categoryName
        })

        initListeners()
    }

    private val navController by lazy { findNavController() }

    private fun initListeners() {
        initDateTimePickers()

        initButtonClickListeners()
    }

    private fun initButtonClickListeners() {
        binding.toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }

        binding.transSumLabel.setOnClickListener {
            navController.navigate(R.id.action_transactionEditingFragment_to_setSumFragment)
        }

        binding.transTypeLabel.setOnClickListener {
            navController.navigate(R.id.action_transactionEditingFragment_to_operationTypeFragment)
        }

        binding.transCategoryLabel.setOnClickListener {
            navController.navigate(R.id.action_transactionEditingFragment_to_transactionCategoryFragment)
        }
    }

    private fun initDateTimePickers() {
        val c = Calendar.getInstance()
        val dateTransaction: LocalDateTime = viewModel.transactionDateTime
        val year = dateTransaction.year
        val month = dateTransaction.monthNumber
        val day = dateTransaction.dayOfMonth
        val hour = dateTransaction.hour
        val minute = dateTransaction.minute
        val localDate = LocalDate(year, month, day)

        binding.transEditingDateLabel.buttonText.text =
            DateUtils.toUIString(localDate, requireContext())
        binding.transEditingTimeLabel.buttonText.text = String.format("%2d:%2d", hour, minute)

        binding.transEditingDateLabel.setOnClickListener {
            showDatePickerDialog(year, month, day).show()
        }

        binding.transEditingTimeLabel.setOnClickListener {
            showTimePickerDialog(c, hour, minute)
        }

        binding.createTransactionButton.setOnClickListener {
            viewModel.updateDate(LocalDateTime(year, month, day, hour, minute, 0, 0))
            viewModel.saveTransaction()
                .observe(viewLifecycleOwner) {
                    navController.navigate(R.id.action_transactionEditingFragment_to_mainFragment)
                }
        }
    }

    private fun showTimePickerDialog(c: Calendar, hour: Int, minute: Int) {
        var hour1 = hour
        var minute1 = minute
        Instant.fromEpochMilliseconds(c.timeInMillis)
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, pHour, pMinute ->
            c.set(Calendar.HOUR_OF_DAY, pHour)
            c.set(Calendar.MINUTE, pMinute)
            binding.transEditingTimeLabel.buttonText.text =
                SimpleDateFormat("HH:mm").format(c.time)
            hour1 = pHour
            minute1 = pMinute
        }
        TimePickerDialog(
            requireContext(),
            timeSetListener,
            c.get(Calendar.HOUR_OF_DAY),
            c.get(Calendar.MINUTE),
            true
        ).show()
    }

    private fun showDatePickerDialog(
        year: Int,
        month: Int,
        day: Int
    ): DatePickerDialog {
        var year1 = year
        var month1 = month
        var day1 = day
        return DatePickerDialog(requireContext(), { view, dYear, dMonthOfYear, dDayOfMonth ->
            binding.transEditingDateLabel.buttonText.text =
                DateUtils.toUIString(
                    LocalDate(dYear, dMonthOfYear + 1, dDayOfMonth),
                    requireContext()
                )
            year1 = dYear
            month1 = dMonthOfYear + 1
            day1 = dDayOfMonth
        }, year1, month1 - 1, day1)
    }
}
