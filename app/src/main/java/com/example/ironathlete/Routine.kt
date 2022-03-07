package com.example.ironathlete

import java.io.Serializable

data class Routine(
    //val background: Int,
    val name: String,
    val exerciseList: List<Exercise>,
):Serializable