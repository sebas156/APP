package com.example.ironathlete.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ironathlete.server.ServerRepositories.ServerUserRepository
import com.example.ironathlete.server.UserObject
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private var serverRepository = ServerUserRepository()
    private lateinit var loadedUser : DocumentSnapshot

    private val loadedUserId: MutableLiveData<String> = MutableLiveData()
    val loadedUserIdDone: LiveData<String> = loadedUserId

    private val userLoadedIndicated : MutableLiveData<Boolean> = MutableLiveData()
    val userLoadedDone: LiveData<Boolean> = userLoadedIndicated
    
    fun getUserId() {
        GlobalScope.launch(Dispatchers.IO){
            loadedUserId.postValue(serverRepository.getUserId())
        }
    }

    fun getUser(userId: String) {
        GlobalScope.launch(Dispatchers.IO){
            loadedUser = serverRepository.getUser(userId)
            userLoadedIndicated.postValue(true)
        }
    }

    fun setUserLoaded(): UserObject? {
        return loadedUser.toObject<UserObject>()
    }


}