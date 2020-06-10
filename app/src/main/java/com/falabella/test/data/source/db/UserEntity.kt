package com.falabella.test.data.source.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.falabella.test.domain.model.User

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val email: String,
    val name: String,
    val password: String?
) {
    companion object {
        fun fromUser(user: User) = UserEntity(0L, user.email, user.name, user.password)
    }
    fun toUser() = User(id, name, email, null)
}