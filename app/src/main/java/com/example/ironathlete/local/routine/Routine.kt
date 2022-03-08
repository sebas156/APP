package com.example.ironathlete.local.routine

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.ironathlete.local.exercise.Exercise
import java.io.Serializable
import java.sql.Types

@Entity(tableName = "Routine")
data class Routine(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="id") var id: Int = Types.NULL,
    //val background: Int,
    @ColumnInfo(name="name")var name: String="",
    @Ignore var exerciseList: List<Exercise> = ArrayList()
):Serializable