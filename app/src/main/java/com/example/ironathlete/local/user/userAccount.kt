package com.example.ironathlete.local.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.sql.Types.NULL

@Entity(tableName = "table_users")
data class userAccount(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="id") var id: Int = NULL,
    @ColumnInfo(name="email") var email: String="",
    @ColumnInfo(name="password") var password: String="",
    @ColumnInfo(name="fullName") var fullName: String = "",
    @ColumnInfo (name="weight") var weight: Double = 0.0,
    @ColumnInfo (name="height") var height: Double = 0.0,
    @ColumnInfo (name = "gender") var gender :String = "",
    @ColumnInfo (name="hourWorkOut") var hourWorkOut: String ="",
    @ColumnInfo (name="goToGym") var goToGym: Boolean = false,
    @ColumnInfo (name="objetive") var objetive: String = "",
    @ColumnInfo (name = "requiredProtein") var requiredProtein: Double = 0.0,
    @ColumnInfo (name = "requiredCarbs") var requiredCarbs: Double = 0.0,
    @ColumnInfo (name = "requiredFats") var requiredFats: Double = 0.0

):Serializable