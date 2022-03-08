package com.example.ironathlete.local.user

import androidx.room.*

@Dao
interface UserDao {

    @Insert
    suspend fun saveUser(user: userAccount)

    @Query("SELECT * FROM table_users WHERE email LIKE :userEmail")
    suspend fun searchUser(userEmail:String): userAccount

}