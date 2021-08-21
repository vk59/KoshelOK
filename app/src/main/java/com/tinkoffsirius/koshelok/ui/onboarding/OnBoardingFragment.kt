package com.tinkoffsirius.koshelok.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.databinding.FragmentOnBoardingBinding
import com.tinkoffsirius.koshelok.repository.AccountSharedRepository
import com.tinkoffsirius.koshelok.repository.SharedPreferencesFactory
import com.tinkoffsirius.koshelok.repository.SharedPreferencesFactory.Companion.ACCOUNT_DATA
import timber.log.Timber

class OnBoardingFragment : Fragment() {

    private val binding by viewBinding(FragmentOnBoardingBinding::bind)
    private val loginResultHandler =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->

            val task = GoogleSignIn.getSignedInAccountFromIntent(result?.data)
            val account = task.result

            navigateWith(account)
        }

    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_on_boarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButton()
    }

    private fun initButton() {
        binding.signInButton.setOnClickListener {
            loginResultHandler.launch(getSignInIntent())
        }
    }

    private fun getSignInIntent(): Intent {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        return mGoogleSignInClient.signInIntent
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(requireContext())
        Timber.d("${account?.displayName}  ${account?.email}")
        account?.let {
            navigateWith(it)
        }
    }

    private fun navigateWith(account: GoogleSignInAccount) {
        AccountSharedRepository(SharedPreferencesFactory().create(requireContext(), ACCOUNT_DATA)).saveAccount(account)
        navController.navigate(R.id.action_onBoardingFragment_to_mainFragment)
    }
}