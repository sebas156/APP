package com.example.ironathlete.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.ironathlete.ui.main.MainActivity
import com.example.ironathlete.ui.registro.RegistroActivity
import com.example.ironathlete.databinding.ActivityLoginBinding
import com.example.ironathlete.local.user.userAccount

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding : ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        setContentView(loginBinding.root)

        loginViewModel.msgDone.observe(this, { message ->
            onMsgDoneSuscribe(message)
        })

        loginViewModel.validated.observe(this,{validated ->
            onValidatedSuscribe(validated)
        })

        loginViewModel.findUserDone.observe(this,{result ->
            onFindUserDoneSubscribe(result)
        })


        with(loginBinding){
            SignInButton.setOnClickListener{
                loginViewModel.ValidateFields(UserEmailEditText.text.toString(),UserPasswordEditText.text.toString())
            }

            RegisterTextView.setOnClickListener {
                val intent = Intent(this@LoginActivity, RegistroActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun onValidatedSuscribe(validated: Boolean?) {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun onFindUserDoneSubscribe(result: userAccount?) {
        with(loginBinding){
            if (result != null) {
                loginViewModel.validateAccout(result.email,result.password,UserEmailEditText.text.toString(),UserPasswordEditText.text.toString())
            }else{
                Toast.makeText(this@LoginActivity,"El usuario ingresado no se encuentra dentro de nuestra base de datos.",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onMsgDoneSuscribe(message: String?) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}