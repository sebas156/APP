package com.example.ironathlete.ui.comunity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ironathlete.server.ComunityObject
import com.example.ironathlete.server.ServerRepositories.ServerCommunityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CommunityViewModel : ViewModel() {

    private val serverCommunityRepository = ServerCommunityRepository()

    private val updatedPublication: MutableLiveData<ComunityObject> = MutableLiveData()
    var updatedPublicationDone = updatedPublication


    fun publicateContent(
        content: String,
        fecha: String,
        nameUser: String?,
        ageUser: Int?,
        objetiveUser: String?
    ) {
        val comments:ArrayList<String> = ArrayList()
        val newPublication = ComunityObject("",nameUser,content,"aqui va el link de la foto",0,comments,ageUser, fecha,objetiveUser)
        GlobalScope.launch(Dispatchers.IO){
            serverCommunityRepository.createNewPublication(newPublication)
            updatedPublication.postValue(newPublication)
        }
    }


}