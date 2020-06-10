package com.falabella.test.domain.usecase

import com.falabella.test.domain.model.User
import com.falabella.test.domain.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) : BaseUseCase<LoginUseCase.Params, User?>() {
    class Params (
        val email: String,
        val password: String
    )

    override suspend fun invoke(params: Params): User? = authRepository.login(params.email, params.password)
}