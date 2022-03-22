package com.example.ironathlete.ui.infoUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ironathlete.server.ServerRepositories.ServerUserRepository
import com.example.ironathlete.server.UserObject
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class InfoUserViewModel : ViewModel() {
    private var serverRepository = ServerUserRepository()
    private lateinit var loadedUser : DocumentSnapshot

    private val userLoadedIndicated : MutableLiveData<Boolean> = MutableLiveData()
    val userLoadedDone: LiveData<Boolean> = userLoadedIndicated

    private val loadedUserId: MutableLiveData<String> = MutableLiveData()
    val loadedUserIdDone: LiveData<String> = loadedUserId

    fun getUser(userid: String){
        GlobalScope.launch(Dispatchers.IO){
            loadedUser = serverRepository.getUser(userid)
            userLoadedIndicated.postValue(true)
        }
    }

    fun setUserLoaded():UserObject? {
        val settedUser:UserObject? = loadedUser.toObject<UserObject>()
        return settedUser
    }

    fun getUserId() {
        GlobalScope.launch(Dispatchers.IO){
            loadedUserId.postValue(serverRepository.getUserId())
        }
    }
}