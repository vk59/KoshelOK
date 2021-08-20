package com.tinkoff_sirius.koshelok.ui.on_boarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.tinkoff_sirius.koshelok.R
import com.tinkoff_sirius.koshelok.databinding.FragmentOnBoardingBinding
import com.tinkoff_sirius.koshelok.repository.AccountShared
import com.tinkoff_sirius.koshelok.repository.SharedPreferencesFactory
import com.tinkoff_sirius.koshelok.ui.main.MainViewModel
import timber.log.Timber

class OnBoardingFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()
    private val binding by viewBinding(FragmentOnBoardingBinding::bind)
    private val loginResultHandler =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->

            val task = GoogleSignIn.getSignedInAccountFromIntent(result?.data)
            val account = task.result

            navigateWith(account)
        }

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
        Timber.tag("ACCOUNT").d("${account?.displayName}  ${account?.email}")
        if (account != null) {
            navigateWith(account)
        }
    }

    private fun navigateWith(account: GoogleSignInAccount) {
        AccountShared(SharedPreferencesFactory().create(requireContext())).saveAccount(account)
        findNavController().navigate(R.id.action_onBoardingFragment_to_mainFragment)
    }
}
