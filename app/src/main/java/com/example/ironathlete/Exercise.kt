package com.example.ironathlete

import java.io.Serializable

data class Exercise(
    val name: String,
    val image: Int,
    val repetitions: Int,
    val weight: Float,
    val description: String,
    val gif :Int,
):Serializable
