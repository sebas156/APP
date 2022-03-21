package com.example.ironathlete.local.repository

import com.example.ironathlete.IronAtlheteProject
import com.example.ironathlete.local.user.UserDao
import com.example.ironathlete.local.user.userAccount
import java.sql.Types.NULL

class UserRepository {

    suspend fun saveUser(emailUser: String, passWordUser: String){
        val user = userAccount(
            id=NULL,
            email=emailUser,
            password = passWordUser
        )

        val userDao: UserDao = IronAtlheteProject.databaseUser.UserDao()
        userDao.saveUser(user)
    }

    suspend fun searchUser(emailUser: String): userAccount {
        val userDao: UserDao = IronAtlheteProject.databaseUser.UserDao()
        val userFound= userDao.searchUser(emailUser)
        return userFound
    }
}