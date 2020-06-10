package com.falabella.test.data.source.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDAO {
    @Query(value = "SELECT * FROM user WHERE email = :email AND password = :password")
    suspend fun validateCredentials(email: String, password: String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUserEntity(userEntity: UserEntity): Long

    @Query(value = "SELECT * FROM user WHERE id = :id")
    suspend fun getUserEntity(id: Long): UserEntity?

    @Query(value = "SELECT * FROM user")
    suspend fun getAllUsers(): List<UserEntity>
}