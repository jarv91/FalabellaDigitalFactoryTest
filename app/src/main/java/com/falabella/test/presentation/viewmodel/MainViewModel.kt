package com.falabella.test.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.falabella.test.di.AppModule
import com.falabella.test.di.DaggerViewModelComponent
import com.falabella.test.domain.model.EconomicIndicator
import com.falabella.test.domain.usecase.GetIndicatorsUseCase
import com.falabella.test.domain.usecase.LogoutUseCase
import com.falabella.test.domain.usecase.None
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel(application: Application) : BaseViewModel(application) {
    @Inject
    lateinit var logoutUseCase: LogoutUseCase

    init {
        DaggerViewModelComponent.builder().appModule(AppModule(getApplication()))
            .build()
            .inject(this)
    }

    fun logout() {
        coroutineScope.launch {
            isLoading.postValue(true)
            logoutUseCase.invoke(None())
            isLoading.postValue(false)
        }
    }
}