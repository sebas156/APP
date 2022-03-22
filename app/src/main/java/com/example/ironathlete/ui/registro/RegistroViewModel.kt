package com.example.ironathlete.ui.registro

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ironathlete.local.repository.UserRepository
import com.example.ironathlete.server.ServerRepositories.ServerUserRepository
import com.example.ironathlete.server.UserObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class RegistroViewModel : ViewModel() {

    //private val userRepository = UserRepository() Room

    private val serverUserRepository = ServerUserRepository()

    private val msg : MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg

    private val dataValidade :MutableLiveData<Boolean> = MutableLiveData()
    var dataValidated : LiveData<Boolean> = dataValidade

    private val registerSuccess: MutableLiveData<String?> = MutableLiveData()
    var registerSuccessDone : LiveData<String?> = registerSuccess

    fun validateFields(emailUser: String, passWordUser: String,RepassWordUser: String) {
        if(emailUser.isEmpty() || passWordUser.isEmpty() || RepassWordUser.isEmpty()){
            msg.value="No puede dejar los campos vacíos"
        } else if(!validarEmail(emailUser)){
            msg.value="Debe ingresar un email valido."
        }else if(passWordUser.length < 6 || RepassWordUser.length <6){
            msg.value="La contraseña debe ser de 6 o mas digitos"
        } else if(passWordUser != RepassWordUser) {
            msg.value="Las contraseñas deben coincidir"
        }else{
            dataValidade.value = true
        }
    }


    fun saveUser(
        emailUser: String,
        passWordUser: String
    ){
        GlobalScope.launch(Dispatchers.IO){
            //userRepository.saveUser(emailUser,passWordUser) Room
            when(val result=serverUserRepository.registerUser(emailUser,passWordUser)){
                "The email address is already in use by another account." -> msg.postValue("Ya existe una cuente con ese correo")
                "The given password is invalid. [ Password should be at least 6 characters ]" -> msg.postValue("La contraseña debe tener minimo 6 digitos")
                "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> msg.postValue("No tiene conexion a internet.")
                else -> registerSuccess.postValue(result)
            }
        }
    }

    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    fun createUser(uid: String?, email: String) {
        GlobalScope.launch(Dispatchers.IO){
            serverUserRepository.createUser(uid,email)
        }
    }

}