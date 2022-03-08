package com.example.ironathlete.local.routine

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Routine::class], version = 1)
abstract class RoutineDatabase :RoomDatabase() {
    abstract fun RoutineDao(): RoutineDao
}