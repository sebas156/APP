package com.example.ironathlete.local

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
):Serializable