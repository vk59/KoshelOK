package com.tinkoffsirius.koshelok.ui.onboarding

import android.content.Context
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
import com.tinkoffsirius.koshelok.R
import com.tinkoffsirius.koshelok.appComponent
import com.tinkoffsirius.koshelok.databinding.FragmentOnBoardingBinding
import com.tinkoffsirius.koshelok.di.modules.ViewModelFactory
import com.tinkoffsirius.koshelok.utils.ErrorSnackbarFactory
import com.tinkoffsirius.koshelok.utils.Event
import timber.log.Timber
import java.net.ConnectException
import javax.inject.Inject

class OnBoardingFragment : Fragment() {

    private val binding by viewBinding(FragmentOnBoardingBinding::bind)

    @Inject
    lateinit var onBoardingViewModelFactory: ViewModelFactory

    private val onBoardingViewModel: OnBoardingViewModel by viewModels(
        factoryProducer = { onBoardingViewModelFactory }
    )

    private val loginResultHandler =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result?.data)
            try {
                authorizeWithAccount(task.result)
            } catch (exception: ConnectException) {
                ErrorSnackbarFactory(binding.root).create(
                    R.drawable.ic_connection_off,
                    getString(R.string.no_connection)
                ).show()
            } catch (exception: Exception) {
                ErrorSnackbarFactory(binding.root).create(
                    R.drawable.ic_warning,
                    getString(R.string.something_went_wrong)
                ).show()
            }
        }

    private val navController by lazy { findNavController() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(requireContext())
        Timber.d("${account?.displayName}  ${account?.email}")
        account?.let {
            authorizeWithAccount(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_on_boarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButton()
        binding.swipeLayout.isEnabled = false
        observeStatus()
    }

    private fun observeStatus() {
        onBoardingViewModel.status.observe(viewLifecycleOwner) {
            when (it) {
                is Event.Error -> {
                    binding.swipeLayout.isRefreshing = false
                    Timber.e(it.throwable)
                    ErrorSnackbarFactory(binding.root)
                        .create(R.drawable.ic_warning, getString(R.string.something_went_wrong))
                        .show()
                }
                is Event.Success -> {
                    binding.swipeLayout.isRefreshing = false
                }
                is Event.Loading -> {
                    binding.swipeLayout.isRefreshing = true
                }
            }
        }
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

    private fun authorizeWithAccount(account: GoogleSignInAccount) {
        onBoardingViewModel.authorize(account).observe(viewLifecycleOwner) {
                navController.navigate(R.id.action_onBoardingFragment_to_walletListFragment)
            }
    }
}
