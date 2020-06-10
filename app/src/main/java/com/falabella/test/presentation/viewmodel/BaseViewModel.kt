package com.falabella.test.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    enum class Errors {
        VALID,
        REQUIRED,
        INVALID,
        TOO_SHORT
    }

    val isLoading = MutableLiveData<Boolean>()
    protected val coroutineScope = CoroutineScope(Dispatchers.IO)
}