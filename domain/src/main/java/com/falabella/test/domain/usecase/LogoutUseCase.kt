package com.falabella.test.domain.usecase

import com.falabella.test.domain.repository.AuthRepository

class LogoutUseCase(private val authRepository: AuthRepository) : BaseUseCase<None, Unit>() {
    override suspend fun invoke(params: None): Unit = authRepository.logout()
}