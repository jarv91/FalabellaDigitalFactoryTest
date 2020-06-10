package com.falabella.test.presentation.ui.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.falabella.test.R
import com.falabella.test.databinding.ActivityAuthBinding
import com.falabella.test.presentation.ui.main.MainActivity
import com.falabella.test.presentation.viewmodel.BaseViewModel
import com.falabella.test.presentation.viewmodel.SignInViewModel

class AuthActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, AuthActivity::class.java)
    }

    private lateinit var binding: ActivityAuthBinding
    private lateinit var viewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth)
    }

    override fun onStart() {
        super.onStart()
        setupViewModel()
        observeViewModel()
        setupLayout()
        viewModel.checkIfUserLogged()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(SignInViewModel::class.java)
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@AuthActivity
        }
    }

    private fun observeViewModel() {
        viewModel.apply {
            emailError.observe(
                this@AuthActivity,
                Observer {
                    it?.let {
                        when (it) {
                            BaseViewModel.Errors.REQUIRED -> binding.etEmail.error =
                                "Campo obligatorio"
                            BaseViewModel.Errors.INVALID -> binding.etEmail.error =
                                " Correo inválido"
                            else -> binding.etEmail.error = null
                        }
                    }
                }
            )
            passwordError.observe(
                this@AuthActivity,
                Observer {
                    it?.let {
                        when (it) {
                            BaseViewModel.Errors.REQUIRED -> binding.etPassword.error =
                                "Campo obligatorio"
                            else -> binding.etPassword.error = null
                        }
                    }
                }
            )
            formError.observe(
                this@AuthActivity,
                Observer {
                    when (it) {
                        SignInViewModel.FormErrors.INVALID_CREDENTIALS -> binding.tvFormError.text =
                            "Usuario o contraseña incorrectos"
                        else -> binding.tvFormError.text = null
                    }
                }
            )
            isLoading.observe(
                this@AuthActivity,
                Observer {
                    it?.let {
                        if (it) {
                            binding.pbLoader.visibility = View.VISIBLE
                        } else {
                            binding.pbLoader.visibility = View.GONE
                        }
                    }
                }
            )
            loggedUser.observe(
                this@AuthActivity,
                Observer {
                    it?.let {
                        startActivity(MainActivity.newIntent(this@AuthActivity))
                        finish()
                    }
                }
            )
            userCreated.observe(
                this@AuthActivity,
                Observer {
                    it?.let {
                        Toast.makeText(
                            applicationContext,
                            "Usuario reseteado satisfactoriamente.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } ?: run {
                        Toast.makeText(
                            applicationContext,
                            "Hubo un error al resetear el usuario.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )
        }
    }

    private fun setupLayout() {
        binding.apply {
            btSignIn.setOnClickListener {
                viewModel.signIn()
            }
            btRestartUser.setOnClickListener {
                viewModel.restartUser()
            }
        }
    }
}