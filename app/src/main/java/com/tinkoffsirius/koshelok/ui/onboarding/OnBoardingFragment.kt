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
import com.tinkoffsirius.koshelok.di.ViewModelFactory
import com.tinkoffsirius.koshelok.repository.AccountSharedRepository
import com.tinkoffsirius.koshelok.ui.ErrorSnackbarFactory
import com.tinkoffsirius.koshelok.ui.Event
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.kotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

class OnBoardingFragment : Fragment() {

    private val binding by viewBinding(FragmentOnBoardingBinding::bind)

    @Inject
    lateinit var accountShared: AccountSharedRepository

    @Inject
    lateinit var onBoardingViewModelFactory: ViewModelFactory

    private val onBoardingViewModel: OnBoardingViewModel by viewModels(
        factoryProducer = { onBoardingViewModelFactory }
    )

    private val loginResultHandler =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result?.data)
            Completable.fromCallable {
                authorizeWithAccount(task.result)
            }
                .subscribeBy(
                    onError = Timber::e
                )
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
        observeStatus()
    }

    private fun observeStatus() {
        onBoardingViewModel.status.observe(viewLifecycleOwner) {
            when (it) {
                is Event.Error -> {
                    Timber.e(it.throwable)
                    ErrorSnackbarFactory(binding.root)
                        .create(R.drawable.ic_warning, getString(R.string.something_went_wrong))
                        .show()
                }
                is Event.Success ->
                    navController.navigate(R.id.action_onBoardingFragment_to_walletListFragment)
                else -> {
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
        onBoardingViewModel.authorize(account)
//        onBoardingViewModel.authorize()
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribeBy (
//                onComplete = {
//                    navController.navigate(R.id.action_onBoardingFragment_to_walletListFragment)
//                },
//                onError = Timber::e
//    )
    }
}
