package com.example.ironathlete.ui.infoUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ironathlete.databinding.FragmentInfoUserBinding
import com.example.ironathlete.server.ServerRepositories.ServerUserRepository
import com.example.ironathlete.server.UserObject
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class InfoUserViewModel : ViewModel() {
    private var serverRepository = ServerUserRepository()
    private lateinit var loadedUser : DocumentSnapshot

    private val userUpdated : MutableLiveData<Boolean> = MutableLiveData()
    val userUpdatedDone: LiveData<Boolean> = userUpdated

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

        return if (objetive == "bulk") caloricRequirement * 1.2
        else caloricRequirement * 0.8
    }

    fun calcularProteinasRequeridas(weight: Double?,levelExercise: Int?): Double {
        var proteins = weight!!

        when (levelExercise) {
            1 -> proteins*=1.2
            2 -> proteins*=1.5
            3 -> proteins*=1.7
            4 -> proteins*=2.5
            else -> proteins*=3
        }
        return proteins
    }

    fun calcularGrasasRequeridas(weight: Double?,levelExercise: Int?): Double? {
        var fats = weight!!

        when (levelExercise) {
            1 -> fats*=0.5
            2 -> fats*=0.6
            3 -> fats*=0.7
            4 -> fats*=0.8
            else -> fats*=1
        }
        return fats
    }

    fun calcularCarbsRequeridos(requiredProtein: Double?, requiredFats: Double?, caloricObjective: Double?): Double? {
        return (caloricObjective!! - requiredProtein!!*4 - requiredFats!!*9)/4
    }

}