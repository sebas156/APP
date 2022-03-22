package com.example.ironathlete.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ironathlete.local.user.userAccount
import com.example.ironathlete.local.repository.UserRepository
import com.example.ironathlete.server.ServerRepositories.ServerUserRepository
import com.example.ironathlete.server.UserObject
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {
    //private val repository = UserRepository() Room

    private val serverUserRepository = ServerUserRepository()
    private lateinit var currentUser : DocumentSnapshot

    private val msg : MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg

    private val SignInSuccess: MutableLiveData<String?> = MutableLiveData()
    val SignInSuccessDone: LiveData<String?> = SignInSuccess

    private val userLoaded: MutableLiveData<Boolean> = MutableLiveData()
    val userLoadedDone: LiveData<Boolean> = userLoaded

    private val msgGreeting : MutableLiveData<String> = MutableLiveData()
    val msgGreetingDone : LiveData<String> = msgGreeting

    /*Room
    private val validate: MutableLiveData<Boolean> = MutableLiveData()
    val validated: LiveData<Boolean> = validate

    private val findUser : MutableLiveData<userAccount?> = MutableLiveData() Room
    val findUserDone : MutableLiveData<userAccount?> = findUser


    fun searchUser(userEmail: String) {
        GlobalScope.launch(Dispatchers.IO){
            //findUser.postValue(repository.searchUser(userEmail)) Room
        }
    }
    */


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
            //searchUser(email) Room
            GlobalScope.launch(Dispatchers.IO){
                when(val result=serverUserRepository.singInUser(email,password)){
                    "There is no user record corresponding to this identifier. The user may have been deleted." -> msg.postValue("No existe un usuario con este correo")
                    "The password is invalid or the user does not have a password." -> msg.postValue("La contraseña es incorrecta.")
                    "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> msg.postValue("No tiene conexion a internet.")
                    else -> SignInSuccess.postValue(result)
                }
            }
        }
    }

    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    fun getUser(userID: String) {
        GlobalScope.launch(Dispatchers.IO) {
            currentUser = serverUserRepository.getUser(userID)
            userLoaded.postValue(true)
        }
    }

    fun greeting(){
        val objectCurrentUser : UserObject? = currentUser.toObject<UserObject>()
        if(objectCurrentUser?.fullName == ""){
            msgGreeting.value="Bienvenido Nuevo Usuario. LLena tus datos en la sección informacion personal"
        }
        else{
            msg.value="Bienvenido "+objectCurrentUser?.fullName+"."
        }
    }




    /*
    fun validateAccout(emailOrigin: String,passwordOrigin: String, email: String, password: String) {
        if(email == emailOrigin && password ==passwordOrigin){
            validate.value=true
        }else{
            msg.value="Usuario o contraseña incorrectos"
        }
    }*/


}