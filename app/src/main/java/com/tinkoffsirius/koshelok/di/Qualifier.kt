package com.tinkoffsirius.koshelok.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class WalletShared

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class TransShared

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AccountShared

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class NewCategoryShared
