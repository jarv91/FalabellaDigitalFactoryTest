package com.falabella.test.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.falabella.test.di.AppModule
import com.falabella.test.di.DaggerViewModelComponent
import com.falabella.test.domain.model.EconomicIndicator
import com.falabella.test.domain.usecase.GetIndicatorsUseCase
import com.falabella.test.domain.usecase.None
import kotlinx.coroutines.launch
import javax.inject.Inject

class EconomicIndicatorListViewModel(application: Application) : BaseViewModel(application) {
    @Inject
    lateinit var getIndicatorsUseCase: GetIndicatorsUseCase

    val economicIndicatorList = MutableLiveData<List<EconomicIndicator>>()

    init {
        DaggerViewModelComponent.builder().appModule(AppModule(getApplication()))
            .build()
            .inject(this)
    }

    fun getEconomicIndicators() {
        coroutineScope.launch {
            isLoading.postValue(true)
            val list = getIndicatorsUseCase.invoke(None())
            economicIndicatorList.postValue(list)
            isLoading.postValue(false)
        }
    }
}