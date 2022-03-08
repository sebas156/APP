package com.example.ironathlete

import com.example.ironathlete.local.userAccount
import java.io.Serializable

data class Community(
    val title: String,
    val description: String,
    val weight : Float,
    val likes : Int,
    val user : userAccount

):Serializable
