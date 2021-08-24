package com.tinkoffsirius.koshelok.di

import com.tinkoffsirius.koshelok.ui.createwallet.editwallet.WalletEditingFragment
import com.tinkoffsirius.koshelok.ui.createwallet.setcurrency.SetCurrencyFragment
import com.tinkoffsirius.koshelok.ui.createwallet.setlimit.SetLimitWalletFragment
import com.tinkoffsirius.koshelok.ui.createwallet.setname.SetNameWalletFragment
import com.tinkoffsirius.koshelok.ui.main.MainFragment
import com.tinkoffsirius.koshelok.ui.newcategory.CategoryNameFragment
import com.tinkoffsirius.koshelok.ui.newcategory.NewCategoryFragment
import com.tinkoffsirius.koshelok.ui.newcategory.NewTypeFragment
import com.tinkoffsirius.koshelok.ui.onboarding.OnBoardingFragment
import com.tinkoffsirius.koshelok.ui.operation.OperationTypeFragment
import com.tinkoffsirius.koshelok.ui.setsum.SetSumFragment
import com.tinkoffsirius.koshelok.ui.transactioncategory.TransactionCategoryFragment
import com.tinkoffsirius.koshelok.ui.transactionediting.TransactionEditingFragment
import com.tinkoffsirius.koshelok.ui.walletlist.WalletListFragment

interface InjectFragments {

    fun inject(mainFragment: MainFragment)

    fun inject(walletEditingFragment: WalletEditingFragment)

    fun inject(setCurrencyFragment: SetCurrencyFragment)

    fun inject(setLimitWalletFragment: SetLimitWalletFragment)

    fun inject(setNameWalletFragment: SetNameWalletFragment)

    fun inject(newCategoryFragment: NewCategoryFragment)

    fun inject(newTypeFragment: NewTypeFragment)

    fun inject(operationTypeFragment: OperationTypeFragment)

    fun inject(setSumFragment: SetSumFragment)

    fun inject(transactionCategoryFragment: TransactionCategoryFragment)

    fun inject(transactionEditingFragment: TransactionEditingFragment)

    fun inject(walletListFragment: WalletListFragment)

    fun inject(categoryNameFragment: CategoryNameFragment)

    fun inject(onBoardingFragment: OnBoardingFragment)
}
