package com.tinkoffsirius.koshelok.di

import androidx.lifecycle.ViewModel
import com.tinkoffsirius.koshelok.ui.createwallet.CreateWalletViewModel
import com.tinkoffsirius.koshelok.ui.main.MainViewModel
import com.tinkoffsirius.koshelok.ui.newcategory.CategoryNameViewModel
import com.tinkoffsirius.koshelok.ui.newcategory.NewCategoryViewModel
import com.tinkoffsirius.koshelok.ui.onboarding.OnBoardingViewModel
import com.tinkoffsirius.koshelok.ui.transactionediting.TransactionEditingViewModel
import com.tinkoffsirius.koshelok.ui.walletlist.WalletListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @[IntoMap ViewModelKey(MainViewModel::class)]
    fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(WalletListViewModel::class)]
    fun bindWalletListViewModel(walletListViewModel: WalletListViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(TransactionEditingViewModel::class)]
    fun bindTransactionEditingViewModel(transactionEditingViewModel: TransactionEditingViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(CreateWalletViewModel::class)]
    fun bindCreateWalletViewModel(createWalletViewModel: CreateWalletViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(NewCategoryViewModel::class)]
    fun bindNewCategoryViewModel(newCategoryViewModel: NewCategoryViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(CategoryNameViewModel::class)]
    fun bindCategoryNameViewModel(categoryNameViewModel: CategoryNameViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(OnBoardingViewModel::class)]
    fun bindOnBoardingViewModel(onBoardingViewModel: OnBoardingViewModel): ViewModel
}
