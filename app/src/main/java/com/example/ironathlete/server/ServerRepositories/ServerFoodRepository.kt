package com.example.ironathlete.server.ServerRepositories

import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ServerFoodRepository {
    private var db = Firebase.firestore

    suspend fun loadMeals(): QuerySnapshot {
        return withContext(Dispatchers.IO){
            db.collection("meals").get().await()
        }
    }

}