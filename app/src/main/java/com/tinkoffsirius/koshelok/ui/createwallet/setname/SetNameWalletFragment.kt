package com.tinkoffsirius.koshelok.ui.createwallet.setname

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tinkoffsirius.koshelok.databinding.FragmentSetNameWalletBinding

class SetNameWalletFragment : Fragment() {

    private val binding: FragmentSetNameWalletBinding by viewBinding(
        FragmentSetNameWalletBinding::bind
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}