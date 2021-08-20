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
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.databinding.FragmentMainBinding
import com.tinkoff_sirius.koshelok.ui.main.adapters.MainRecyclerAdapter
import com.tinkoff_sirius.koshelok.ui.main.adapters.model.MainItem

class MainFragment : Fragment() {

    private val mainRecyclerAdapter by lazy {
        MainRecyclerAdapter(
            { element -> showDeleteDialog(element) },
            { element -> navController.navigate(R.id.action_mainFragment_to_setSumFragment) }
        )
    }

    private val navController = findNavController()
    private val viewModel: MainViewModel by viewModels()
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
        viewModel.onBackPressed()
        viewModel.exitFlag.observe(viewLifecycleOwner) {
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
        viewModel.loadData()
        viewModel.items.value!!.let { mainRecyclerAdapter.setData(it) }

        setItemsObserving()
    }

    private fun setItemsObserving() {
        viewModel.items.observe(viewLifecycleOwner) {
            mainRecyclerAdapter.setData(it)
        }
    }

    private fun showDeleteDialog(element: MainItem) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Вы действительно хотите удалить запись?")
            .setPositiveButton("Удалить") { _, _ ->
                viewModel.deleteTransaction(element)
            }
            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.cancel()
            }
            .create().show()
    }
}
