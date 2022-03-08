package com.example.ironathlete

import android.app.Application
import androidx.room.Room
import com.example.ironathlete.local.diet.MealDatabase
import com.example.ironathlete.local.exercise.ExerciseDatabase
import com.example.ironathlete.local.routine.RoutineDatabase
import com.example.ironathlete.local.user.UserDatabase


class IronAtlheteProject : Application() {

    companion object{
        lateinit var databaseUser : UserDatabase
        lateinit var databaseMeal : MealDatabase
        lateinit var databaseExercise: ExerciseDatabase
        lateinit var databaseRoutine: RoutineDatabase
    }

    override fun onCreate(){
        super.onCreate()

        databaseUser= Room.databaseBuilder(
            this,
            UserDatabase::class.java,
            "user_dt"
        ).build()

        databaseMeal= Room.databaseBuilder(
            this,
            MealDatabase::class.java,
            "meal_dt"
        ).build()

        databaseExercise= Room.databaseBuilder(
            this,
            ExerciseDatabase::class.java,
            "exercise_dt"
        ).build()

        databaseRoutine= Room.databaseBuilder(
            this,
            RoutineDatabase::class.java,
            "routine_dt"
        ).build()

    }
}