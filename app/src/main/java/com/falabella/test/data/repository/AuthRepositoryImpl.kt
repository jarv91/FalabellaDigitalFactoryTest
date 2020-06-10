package com.falabella.test.data.repository

import android.content.Context
import com.falabella.test.data.source.db.DatabaseService
import com.falabella.test.data.source.db.UserEntity
import com.falabella.test.data.source.sp.SharedPreferencesManager
import com.falabella.test.domain.model.User
import com.falabella.test.domain.repository.AuthRepository

class AuthRepositoryImpl(context: Context) : AuthRepository {
    private val userDAO = DatabaseService.getInstance(context).userDao()
    private val sharedPreferencesManager = SharedPreferencesManager(context)

    override suspend fun createNewUser(user: User): User? {
        val newId = userDAO.addUserEntity(UserEntity.fromUser(user))
        return userDAO.getUserEntity(newId)?.toUser()
    }

    override suspend fun login(email: String, password: String): User? {
        val user = userDAO.validateCredentials(email, password)?.toUser()
        user?.let {
            sharedPreferencesManager.startSession(user)
            return user
        } ?: run {
            return null
        }
    }

    override suspend fun logout() {
        sharedPreferencesManager.clearSession()
    }

    override suspend fun getLoggedUser(): User? {
        return sharedPreferencesManager.getSession()
    }
}