package com.example.ironathlete.server

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.io.Serializable
import java.sql.Types

data class MealObject(
   var mid: String? = "",
   var amountCarbs: Double? = null,
   var amountFats: Double? = null,
   var amountProtein: Double? = null,
   var carbsId: String? = "",
   var description: String? = "",
   var proteinId: String? = "",
   var fatsId: String? = "",
   var image: String? ="",
   var ingredients: String? = "",
   var name: String? = "",
   var preparation: String? = ""
): Serializable