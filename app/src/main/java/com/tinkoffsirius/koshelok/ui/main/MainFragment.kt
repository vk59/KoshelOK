package com.tinkoffsirius.koshelok.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.appComponent
import com.tinkoffsirius.koshelok.databinding.FragmentMainBinding
import com.tinkoffsirius.koshelok.di.modules.ViewModelFactory
import com.tinkoffsirius.koshelok.ui.createtransaction.TransactionEditingViewModel
import com.tinkoffsirius.koshelok.ui.main.adapters.MainRecyclerAdapter
import com.tinkoffsirius.koshelok.ui.main.adapters.model.MainItem
import com.tinkoffsirius.koshelok.utils.DeleteDialog
import com.tinkoffsirius.koshelok.utils.ErrorSnackbarFactory
import com.tinkoffsirius.koshelok.utils.Event
import javax.inject.Inject


class MainFragment : Fragment() {

    private val navController by lazy { findNavController() }

    private val mainRecyclerAdapter by lazy {
        MainRecyclerAdapter(
            { id -> showDeleteDialog(id) },
            { element: MainItem ->
                if (element is MainItem.Transaction) {
                    viewModel.editCurrentTransaction(element)
                    navController.navigate(R.id.action_mainFragment_to_transactionEditingFragment)
                }
            }
        )
    }

    @Inject
    lateinit var mainViewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels(
        factoryProducer = {
            mainViewModelFactory
        }
    )

    @Inject
    lateinit var transactionViewModelFactory: ViewModelFactory

    private val viewModelTransactionEditing: TransactionEditingViewModel by viewModels(
        factoryProducer = {
            transactionViewModelFactory
        }
    )

    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
        initRecycler()
        observeStatus()
    }

    private fun observeStatus() {
        viewModel.status.observe(viewLifecycleOwner) { event ->
            with(binding.swipeLayout) {
                when (event) {
                    is Event.Success -> {
                        isRefreshing = false
                    }
                    is Event.Loading -> {
                        isRefreshing = true
                    }
                    is Event.Error -> {
                        isRefreshing = false
                        ErrorSnackbarFactory(binding.root)
                            .create(R.drawable.ic_warning, "Что-то пошло не так")
                            .show()
                    }
                }
            }
        }

        binding.swipeLayout.setOnRefreshListener {
            viewModel.updateTransactions()
        }
    }

    private fun initButtons() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.buttonAdd.setOnClickListener {
            viewModelTransactionEditing.removeTransaction()
            navController.navigate(R.id.action_mainFragment_to_setSumFragment)
        }
    }

    private fun onBackPressed() {
        navController.navigate(R.id.action_mainFragment_to_walletListFragment)
    }

    private fun initRecycler() {
        binding.recyclerView.apply {
            adapter = mainRecyclerAdapter
            layoutManager = LinearLayoutManager(this@MainFragment.context)
        }

        binding.textNoEntities.visibility = View.GONE
        setIsThereTransactionsObserving()
        setItemsObserving()
    }

    private fun setIsThereTransactionsObserving() {
        viewModel.isThereTransactions.observe(viewLifecycleOwner) { isThereTransactions ->
            if (isThereTransactions) {
                binding.textNoEntities.visibility = View.GONE
            } else {
                binding.textNoEntities.visibility = View.VISIBLE
            }
        }
    }

    private fun setItemsObserving() {
        viewModel.items.observe(viewLifecycleOwner) {
            mainRecyclerAdapter.setData(it)
        }
    }

    private fun showDeleteDialog(id: Long) {
        DeleteDialog(
            "Вы действительно хотите удалить запись?",
            { _, _ -> viewModel.deleteTransactionById(id) },
            { dialog, _ -> dialog.cancel() },
            requireContext()
        )
            .create()
            .show()
    }
}
