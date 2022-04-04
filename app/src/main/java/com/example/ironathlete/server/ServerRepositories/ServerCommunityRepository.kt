package com.example.ironathlete.server.ServerRepositories

import com.example.ironathlete.server.ComunityObject
import com.example.ironathlete.server.UserObject
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ServerCommunityRepository {

    private var db = Firebase.firestore

    suspend fun createNewPublication(newPublication: ComunityObject){
        val document = db.collection("forum").document()
        newPublication.id=document.id
        db.collection("forum").document(newPublication.id.toString()).set(newPublication)
    }

    suspend fun getAllPublications():QuerySnapshot{
        return withContext(Dispatchers.IO){
            db.collection("forum").get().await()
        }
    }

    suspend fun updatePublication(modifitedPublication: ComunityObject){
        db.collection("forum").document(modifitedPublication.id.toString()).set(modifitedPublication)
    }

}