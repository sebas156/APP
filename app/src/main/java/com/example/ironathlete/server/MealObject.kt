package com.example.ironathlete.server

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.io.Serializable
import java.sql.Types

data class MealObject(
    var id: String = "",
    var background: String,
    var nameMeal: String,
    var numberMeal: Int,
    var preparationMeal: String,
    var proteinsMeatMeal: Double,
    var carbsFloursMeal: Double,
    var richFatMeal: Double,
): Serializable