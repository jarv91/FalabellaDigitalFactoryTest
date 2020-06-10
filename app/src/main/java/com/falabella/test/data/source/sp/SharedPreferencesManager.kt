package com.falabella.test.data.source.sp

import android.content.Context
import android.content.SharedPreferences
import com.falabella.test.domain.model.User

class SharedPreferencesManager(val context: Context) {
    private val SP_KEY: String = "falabella_test"
    private val SP_SESSION_EMAIL = "session_email"
    private val SP_SESSION_NAME = "session_name"
    private val SP_SESSION_USER_ID = "session_user_id"

    private lateinit var sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE)
    }

    fun startSession(user: User) {
        sharedPreferences.edit().putString(SP_SESSION_EMAIL, user.email)
            .putString(SP_SESSION_NAME, user.name)
            .putLong(SP_SESSION_USER_ID, user.id)
            .apply()
    }

    fun getSession(): User? {
        val id = sharedPreferences.getLong(SP_SESSION_USER_ID, 0L)
        val name = sharedPreferences.getString(SP_SESSION_NAME, null)
        val email = sharedPreferences.getString(SP_SESSION_EMAIL, null)
        if (id == 0L)
            return null
        return User(id, name!!, email!!, null)
    }

    fun clearSession() {
        sharedPreferences.edit()
            .remove(SP_SESSION_USER_ID)
            .remove(SP_SESSION_EMAIL)
            .remove(SP_SESSION_NAME)
            .apply()
    }
}