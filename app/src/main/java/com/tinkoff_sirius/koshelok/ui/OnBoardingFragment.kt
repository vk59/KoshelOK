package com.tinkoff_sirius.koshelok.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.databinding.OnBoardingFragmentBinding


class OnBoardingFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: OnBoardingFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = OnBoardingFragmentBinding
                .bind(inflater.inflate(R.layout.on_boarding_fragment, container, false))
        initButton()
        return binding.root
    }

    private fun initButton() {
        binding.signInButton.setOnClickListener {
            // TODO: 1
            loginResultHandler.launch(viewModel.getSignInIntent(requireContext()))
        }
    }

    override fun onStart() {
        super.onStart()
        // TODO: 2
        val account = GoogleSignIn.getLastSignedInAccount(requireContext())
        Log.d("ACCOUNT", account?.displayName + account?.email ?: " null ")
        navigateWith(account)
    }

    // TODO: 3
    private val loginResultHandler =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->

                val task = GoogleSignIn.getSignedInAccountFromIntent(result?.data)
                val account = task.result

                navigateWith(account)
            }

    private fun navigateWith(account: GoogleSignInAccount?) {
        if (account != null) {
            findNavController().navigate(R.id.action_onBoardingFragment_to_mainFragment)
            viewModel.account = account
        }
    }
}