package com.example.ironathlete.ui.registro

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ironathlete.local.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class RegistroViewModel : ViewModel() {

    private val userRepository = UserRepository()

    private val msg : MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg

    private val dataValidade :MutableLiveData<Boolean> = MutableLiveData()
    var dataValidated : LiveData<Boolean> = dataValidade

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
            userRepository.saveUser(emailUser,passWordUser)
        }
    }

    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

}