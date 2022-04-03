package com.example.ironathlete.server.ServerRepositories

import com.example.ironathlete.server.MealObject
import com.google.firebase.firestore.DocumentSnapshot
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

    suspend fun setMealInServer(meal: MealObject){
        val documentMeal = db.collection("meals").document()
        meal.mid = documentMeal.id
        db.collection("meals").document(meal.mid.toString()).set(meal).await()
    }

    suspend fun getIngredient() : QuerySnapshot{
        return withContext(Dispatchers.IO){
            db.collection("ingredients").get().await()
        }
    }

}