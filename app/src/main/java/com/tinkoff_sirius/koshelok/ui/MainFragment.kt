package com.tinkoff_sirius.koshelok.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.adapters.MainRecyclerAdapter
import com.tinkoff_sirius.koshelok.databinding.MainFragmentBinding
import com.tinkoff_sirius.koshelok.model.MainAdapterItem

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private val binding by viewBinding(MainFragmentBinding::bind)

    private val recyclerView: RecyclerView by lazy(LazyThreadSafetyMode.NONE) {
        binding.mainRecycler
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainRecyclerAdapter = MainRecyclerAdapter()

        recyclerView.apply {
            adapter = mainRecyclerAdapter
            layoutManager = LinearLayoutManager(this@MainFragment.context)
        }

        val mTransaction = listOf<MainAdapterItem>(
            MainAdapterItem.Header,
            MainAdapterItem.Transaction("aaaaaaaaaa"),
            MainAdapterItem.Transaction("aaaaaaaaaa")
        )
        mainRecyclerAdapter.setData(mTransaction)
    }

}