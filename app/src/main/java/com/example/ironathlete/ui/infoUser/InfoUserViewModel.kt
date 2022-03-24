package com.example.ironathlete.ui.infoUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ironathlete.databinding.FragmentInfoUserBinding
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
    private lateinit var infoUserBinding: FragmentInfoUserBinding
    private lateinit var loadedUser : DocumentSnapshot

    private val userLoadedIndicated : MutableLiveData<Boolean> = MutableLiveData()
    val userLoadedDone: LiveData<Boolean> = userLoadedIndicated

    private val userUpdated : MutableLiveData<Boolean> = MutableLiveData()
    val userUpdatedDone: LiveData<Boolean> = userUpdated

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

    fun updateInfoUser(currentUser: UserObject) {
        GlobalScope.launch(Dispatchers.IO){
            serverRepository.updateUser(currentUser)
            userUpdated.postValue(true)
        }
    }

    fun calcularRequerimientoCalorico(weight: Double?, gender: String?, height: Double?, age: Int?, levelExercise: Int?) : Double {
        var requerimeinto = (10* weight!!)+(6.25* height!!) - (5* age!!)

        if(gender == "Hombre") requerimeinto+=5
        else requerimeinto -=161

        requerimeinto *= when(levelExercise){
            1 -> 1.2
            2 -> 1.375
            3 -> 1.55
            4 -> 1.725
            else -> 1.9
        }

        return requerimeinto
    }

    fun calcularObjetivoClorico(caloricRequirement: Double, objetive: String?): Double? {
        var caloricObjetive = if(objetive =="bulk") caloricRequirement*1.2
        else caloricRequirement*0.8

        return caloricObjetive
    }

    fun calcularProteinasRequeridas(objetive: String?, weight: Double?): Double? {
        if(objetive == "shredded") if (weight != null) {
            return weight*3
        }
        return weight!! *2.5
    }

    fun calcularGrasasRequeridas(objetive: String?, weight: Double?): Double? {
        if(objetive == "shredded") if (weight != null) {
            return weight
        }
        return weight!! *0.8
    }

    fun calcularCarbsRequeridos(requiredProtein: Double?, requiredFats: Double?, caloricObjective: Double?): Double? {
        return (caloricObjective!! - requiredProtein!!*4 - requiredFats!!*9)/4
    }

}