package com.example.ironathlete

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ironathlete.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        var emailReceived: String?= ""
        var passwordReceived: String? = ""

        val credentials =intent.extras
        if(credentials != null){
            emailReceived =credentials.getString("email1")
            passwordReceived =credentials.getString("password1")
        }

        with(loginBinding){
            SignInButton.setOnClickListener{
                val email = UserEmailEditText.text.toString()
                val password = UserPasswordEditText.text.toString()
                if(email== emailReceived && password == passwordReceived && email.isNotEmpty() && password.isNotEmpty()){
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("email",email)
                    intent.putExtra("password",password)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this@LoginActivity,"Usuario o contraseña incorrecto",Toast.LENGTH_SHORT).show()
                }
            }

            RegisterTextView.setOnClickListener {
                val intent = Intent(this@LoginActivity, RegistroActivity::class.java)
                startActivity(intent)
            }
        }
    }
}