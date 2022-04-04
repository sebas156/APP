package com.example.ironathlete.ui.comunity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ironathlete.server.ComunityObject
import com.example.ironathlete.server.ServerRepositories.ServerCommunityRepository
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CommunityViewModel : ViewModel() {

    private val serverCommunityRepository = ServerCommunityRepository()
    private val socialList : ArrayList<ComunityObject> = ArrayList()

    private val getPublications: MutableLiveData<ArrayList<ComunityObject>> = MutableLiveData()
    var getPublicationsDone = getPublications

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

    fun getAllPublication(){
        socialList.clear()
        GlobalScope.launch(Dispatchers.IO){
            val documents = serverCommunityRepository.getAllPublications()
            for(document in documents){
                val publication = document.toObject<ComunityObject>()
                socialList.add(publication)
            }
            getPublications.postValue(socialList)
        }
    }

    fun updatePublication(modifatedPublication: ComunityObject){
        GlobalScope.launch(Dispatchers.IO) {
            serverCommunityRepository.updatePublication(modifatedPublication)
        }
    }


}