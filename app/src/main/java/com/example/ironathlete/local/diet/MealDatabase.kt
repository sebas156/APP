package com.example.ironathlete.local.diet

import com.example.ironathlete.local.diet.mealItem
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [mealItem::class], version = 1)
abstract class MealDatabase: RoomDatabase() {
    abstract fun MealDao(): MealDao
}