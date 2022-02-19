package com.example.ironathlete.repository

import com.example.ironathlete.IronAtlheteProject
import com.example.ironathlete.local.UserDao
import com.example.ironathlete.local.userAccount
import java.sql.Types.NULL

class UserRepository {

    suspend fun saveUser(emailUser: String, passWordUser: String){
        val user = userAccount(
            id=NULL,
            email=emailUser,
            password = passWordUser
        )

        val userDao: UserDao = IronAtlheteProject.database.UserDao()
        userDao.saveUser(user)
    }
}