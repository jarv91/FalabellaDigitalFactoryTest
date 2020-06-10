package com.falabella.test.data.source.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class DatabaseService : RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "falabella_demo.db"
        private var instance: DatabaseService? = null
        private fun create(context: Context): DatabaseService = Room.databaseBuilder(context, DatabaseService::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
        fun getInstance(context: Context): DatabaseService = (instance ?: create(context)).also { instance = it }
    }

    abstract fun userDao(): UserDAO
}