package com.example.ironathlete.local.exercise

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities=[Exercise::class],version=1)
abstract class ExerciseDatabase : RoomDatabase() {
    abstract fun ExerciseDao(): ExerciseDao
}