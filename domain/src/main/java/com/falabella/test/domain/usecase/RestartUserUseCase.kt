package com.falabella.test.domain.usecase

import com.falabella.test.domain.model.User
import com.falabella.test.domain.repository.AuthRepository

class RestartUserUseCase(private val authRepository: AuthRepository) : BaseUseCase<String, User?>() {
    override suspend fun invoke(params: String): User? = authRepository.createNewUser(User(0L,"Adrián", "adrian.rodriguez@globant.com", params))
}