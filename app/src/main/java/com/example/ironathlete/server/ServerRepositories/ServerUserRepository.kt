package com.example.ironathlete.server.ServerRepositories

import android.util.Log
import com.example.ironathlete.server.UserObject
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class ServerUserRepository {

    private var auth: FirebaseAuth = Firebase.auth
    private var db = Firebase.firestore
    suspend fun registerUser(emailUser: String, passWordUser: String): String? {
        return try {
            val result = auth.createUserWithEmailAndPassword(emailUser, passWordUser).await() // Si yo coloco el await ya los listener no sirven
            result.user?.uid
        }catch (e: FirebaseAuthException){
            Log.d("errorRegister",e.localizedMessage)
            e.localizedMessage
        }catch (e: FirebaseNetworkException){
            Log.d("errorRegister",e.localizedMessage)
            e.localizedMessage
        }
    }

    suspend fun createUser(uid: String?, email: String) {
        val userCreated = UserObject(uid,email)
        db.collection("users").document(userCreated.uid.toString()).set(userCreated).await()
    }
}