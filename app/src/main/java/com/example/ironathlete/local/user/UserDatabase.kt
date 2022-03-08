package com.example.ironathlete.local.user

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities=[userAccount::class],version=1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun UserDao(): UserDao
}