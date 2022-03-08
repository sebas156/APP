package com.example.ironathlete.local.diet

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.sql.Types

@Entity(tableName = "meals")
data class mealItem (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="id") var id: Int = Types.NULL,
    @ColumnInfo (name="background") var background: Int,
    @ColumnInfo (name="nameMeal") var nameMeal: String,
    @ColumnInfo (name="numberMeal") var numberMeal: Int,
    @ColumnInfo (name="preparationMeal") var preparationMeal: String,
    @ColumnInfo (name="proteinsMeatMeal") var proteinsMeatMeal: Double,
    @ColumnInfo (name="carbsFloursMeal") var carbsFloursMeal: Double,
    @ColumnInfo (name="richFatMeal") var richFatMeal: Double,

    ):Serializable