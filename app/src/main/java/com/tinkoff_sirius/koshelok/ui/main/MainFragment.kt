package com.tinkoff_sirius.koshelok.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.tinkoff_sirius.koshelok.Dependencies
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.databinding.FragmentMainBinding
import com.tinkoff_sirius.koshelok.ui.main.adapters.MainRecyclerAdapter

class MainFragment : Fragment() {

    private val navController by lazy { findNavController() }

    private val mainRecyclerAdapter by lazy {
        MainRecyclerAdapter(
            { id -> showDeleteDialog(id) },
            { element -> navController.navigate(R.id.action_mainFragment_to_setSumFragment) },
            Dependencies.resourceProvider
        )
    }

    private val viewModel: MainViewModel by viewModels(
        factoryProducer = {
            Dependencies.mainViewModelFactory
        }
    )

    private val binding by viewBinding(FragmentMainBinding::bind)

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
    }

    private fun initButtons() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.buttonAdd.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_setSumFragment)
        }
    }

    private fun onBackPressed() {
        viewModel.onBackPressed().observe(viewLifecycleOwner) {
            if (it) {
                activity?.finish()
            } else {
                Snackbar
                    .make(
                        binding.root,
                        getString(R.string.snackbar_push_exit),
                        Snackbar.LENGTH_LONG
                    )
                    .show()
            }
        }
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
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Вы действительно хотите удалить запись?")
            .setPositiveButton("Удалить") { _, _ ->
                viewModel.deleteTransactionById(id)
            }
            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.cancel()
            }
            .create().show()
    }
}
