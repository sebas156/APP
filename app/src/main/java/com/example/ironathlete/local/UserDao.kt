package com.example.ironathlete.local

import androidx.room.*

@Dao
interface UserDao {

    @Insert
    suspend fun saveUser(user:userAccount)

}