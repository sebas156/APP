package com.example.ironathlete.server.ServerRepositories

import android.util.Log
import android.widget.Toast
import com.example.ironathlete.server.UserObject
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

class ServerUserRepository {

    private var auth: FirebaseAuth = Firebase.auth
    private var db = Firebase.firestore
    private var st = FirebaseStorage.getInstance().reference

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

    suspend fun updateUser(user:UserObject){
        db.collection("users").document(user.uid.toString()).set(user).await()
    }

    suspend fun singInUser(email: String, password: String) : String?{
        return try {
            auth.signInWithEmailAndPassword(email, password).await() // Si yo coloco el await ya los listener no sirven
            auth.currentUser?.uid
        }catch (e: FirebaseAuthException){
            Log.d("errorRegister",e.localizedMessage)
            e.localizedMessage
        }catch (e: FirebaseNetworkException){
            Log.d("errorRegister",e.localizedMessage)
            e.localizedMessage
        }
    }

    suspend fun getUser(userID: String): DocumentSnapshot {
        val user = db.collection("users").document(userID)
        return user.get().await()
    }

     fun getUserId(): String? {
        return auth.currentUser?.uid
    }
}