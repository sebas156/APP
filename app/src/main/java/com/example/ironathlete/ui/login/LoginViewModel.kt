package com.example.ironathlete.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ironathlete.local.user.userAccount
import com.example.ironathlete.local.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {
    private val repository = UserRepository()

    private val msg : MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg

    private val validate: MutableLiveData<Boolean> = MutableLiveData()
    val validated: LiveData<Boolean> = validate

    private val findUser : MutableLiveData<userAccount?> = MutableLiveData()
    val findUserDone : MutableLiveData<userAccount?> = findUser

    fun searchUser(userEmail: String) {
        GlobalScope.launch(Dispatchers.IO){
            findUser.postValue(repository.searchUser(userEmail))
        }
    }

    fun ValidateFields(email:String, password:String){
        if(email.isEmpty()==true || password.isEmpty()==true){
            msg.value="Por favor rellene los campos solicitados"
        }
        else if(!validarEmail(email)){
            msg.value="Por favor ingrese un correo valido."
        }
        else if(password.length < 6){
            msg.value = "Ingrese una contraseña valida, mayor de 6 digitos."
        }
        else{
            searchUser(email)
        }
    }

    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    fun validateAccout(emailOrigin: String,passwordOrigin: String, email: String, password: String) {
        if(email == emailOrigin && password ==passwordOrigin){
            validate.value=true
        }else{
            msg.value="Usuario o contraseña incorrectos"
        }
    }


}