package com.example.ironathlete

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.example.ironathlete.local.UserDatabase


class IronAtlheteProject : Application() {

    companion object{
        lateinit var database : UserDatabase
    }

    override fun onCreate(){
        super.onCreate()

        database= Room.databaseBuilder(
            this,
            UserDatabase::class.java,
            "user_dt"
        ).build()

    }
}