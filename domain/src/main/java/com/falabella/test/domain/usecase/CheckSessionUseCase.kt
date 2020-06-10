package com.falabella.test.domain.usecase

import com.falabella.test.domain.model.User
import com.falabella.test.domain.repository.AuthRepository

class CheckSessionUseCase(private val authRepository: AuthRepository) : BaseUseCase<None, User?>() {
    override suspend fun invoke(params: None): User? = authRepository.getLoggedUser()
}