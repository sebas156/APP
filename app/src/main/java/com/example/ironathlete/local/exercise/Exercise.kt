package com.example.ironathlete.local.exercise

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.sql.Types

data class ExerciseFirebase(
    var id : String ="",
    var name: String="",
    var image: String="",
    var repetitions: Int=0,
    var weight: Float=0f,
    var description: String="",
    var gif : String="")




@Entity(tableName = "exercise")
data class Exercise(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="id") var id: Int = Types.NULL,
    @ColumnInfo (name="name") var name: String,
    @ColumnInfo (name="image") var image: Int,
    @ColumnInfo (name="repetitions") var repetitions: Int,
    @ColumnInfo (name="weight") var weight: Float,
    @ColumnInfo (name="description") var description: String,
    @ColumnInfo (name="gif") var gif :Int,
):Serializable
