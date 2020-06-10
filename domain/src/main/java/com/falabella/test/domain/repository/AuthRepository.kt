package com.falabella.test.domain.repository

import com.falabella.test.domain.model.User

interface AuthRepository {
    suspend fun createNewUser(user: User): User?

    suspend fun login(email: String, password: String): User?

    suspend fun logout()

    suspend fun getLoggedUser(): User?
}