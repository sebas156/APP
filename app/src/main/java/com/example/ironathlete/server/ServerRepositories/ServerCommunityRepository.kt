package com.example.ironathlete.server.ServerRepositories

import com.example.ironathlete.server.ComunityObject
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ServerCommunityRepository {

    private var db = Firebase.firestore

    suspend fun createNewPublication(newPublication: ComunityObject){
        val document = db.collection("forum").document()
        newPublication.id=document.id
        db.collection("forum").document(newPublication.id.toString()).set(newPublication)
    }

}