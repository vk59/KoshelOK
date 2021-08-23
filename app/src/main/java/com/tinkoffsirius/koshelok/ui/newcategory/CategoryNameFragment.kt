package com.tinkoffsirius.koshelok.ui.newcategory

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tinkoffsirius.koshelok.R

class CategoryNameFragment : Fragment() {

    private lateinit var viewModel: CategoryNameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.category_name_fragment, container, false)
    }


}