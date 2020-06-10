package com.falabella.test.di

import com.falabella.test.presentation.viewmodel.EconomicIndicatorListViewModel
import com.falabella.test.presentation.viewmodel.MainViewModel
import com.falabella.test.presentation.viewmodel.SignInViewModel
import dagger.Component

@Component(
    modules = [
        AppModule::class,
        RepositoryModule::class,
        UseCasesModule::class
    ]
)
interface ViewModelComponent {
    fun inject(signInViewModel: SignInViewModel)
    fun inject(economicIndicatorListViewModel: EconomicIndicatorListViewModel)
    fun inject(mainViewModel: MainViewModel)
}