package com.falabella.test.presentation.viewmodel

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.falabella.test.di.AppModule
import com.falabella.test.di.DaggerViewModelComponent
import com.falabella.test.domain.model.User
import com.falabella.test.domain.usecase.CheckSessionUseCase
import com.falabella.test.domain.usecase.LoginUseCase
import com.falabella.test.domain.usecase.None
import com.falabella.test.domain.usecase.RestartUserUseCase
import com.falabella.test.util.Encryptor
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInViewModel(application: Application) : BaseViewModel(application) {
    enum class FormErrors {
        INVALID_CREDENTIALS,
    }

    @Inject
    lateinit var loginUseCase: LoginUseCase
    @Inject
    lateinit var restartUserUseCase: RestartUserUseCase
    @Inject
    lateinit var checkSessionUseCase: CheckSessionUseCase

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val formError = MutableLiveData<FormErrors>()
    val isFormValid = MutableLiveData<Boolean>()
    val loggedUser = MutableLiveData<User>()
    val userCreated = MutableLiveData<Boolean>()

    init {
        DaggerViewModelComponent.builder().appModule(AppModule(getApplication()))
            .build()
            .inject(this)
        email.value = "adrian.rodriguez@globant.com"
        password.value = "abc123"
    }

    val emailError = MediatorLiveData<Errors>().apply {
        addSource(email) {
            value = when {
                it.trim().isEmpty() -> Errors.REQUIRED
                !Patterns.EMAIL_ADDRESS.matcher(it).matches() -> Errors.INVALID
                else -> Errors.VALID
            }
            validateForm()
        }
    }
    val passwordError = MediatorLiveData<Errors>().apply {
        addSource(password) {
            value = when {
                it.trim().isEmpty() -> Errors.REQUIRED
                else -> Errors.VALID
            }
            validateForm()
        }
    }

    fun checkIfUserLogged() {
        coroutineScope.launch {
            isLoading.postValue(true)
            val user = checkSessionUseCase.invoke(None())
            user?.let {
                loggedUser.postValue(user)
            }
            isLoading.postValue(false)
        }
    }

    fun signIn() {
        coroutineScope.launch {
            isLoading.postValue(true)
            val user = loginUseCase.invoke(LoginUseCase.Params(email.value!!, Encryptor().encrypt(password.value!!)))
            user?.let {
                loggedUser.postValue(user)
            } ?: run {
                formError.postValue(FormErrors.INVALID_CREDENTIALS)
            }
            isLoading.postValue(false)
        }
    }

    fun restartUser() {
        coroutineScope.launch {
            isLoading.postValue(true)
            val user = restartUserUseCase.invoke(Encryptor().encrypt("abc123"))
            user?.let {
                userCreated.postValue(true)
            } ?: run {
                userCreated.postValue(false)
            }
            isLoading.postValue(false)
        }
    }

    private fun validateForm(): Boolean {
        formError.value = null
        isFormValid.value = emailError.value == Errors.VALID && passwordError.value == Errors.VALID
        return isFormValid.value!!
    }
}