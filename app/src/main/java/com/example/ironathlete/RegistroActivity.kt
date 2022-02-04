package com.example.ironathlete

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Patterns
import android.widget.Toast
import com.example.ironathlete.databinding.ActivityRegistroBinding
import java.util.regex.Pattern


class RegistroActivity : AppCompatActivity() {

    private lateinit var registroBinding : ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registroBinding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(registroBinding.root)

        with(registroBinding){
            RegistroButton.setOnClickListener{
                val email = UserEmailTextEdit2.text.toString()
                val password= passwordTextEdit.text.toString()
                val repassword = repasswordEditText.text.toString()


                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(this@RegistroActivity,"Se deben llenar los campos",Toast.LENGTH_SHORT).show()
                }
                else if(!validarEmail(email)){
                    Toast.makeText(this@RegistroActivity,"Correo invalido: Por favor ingresar un correo valido.",Toast.LENGTH_SHORT).show()
                }
                else if(password.length < 6){
                    Toast.makeText(this@RegistroActivity,"Contraseña demasiado corta, debe ser de al menos 6 caracteres.",Toast.LENGTH_SHORT).show()
                }
                else if(password != repassword){
                    Toast.makeText(this@RegistroActivity,"Las contraseñas deben coincidir", Toast.LENGTH_SHORT).show()
                }
                else{
                    val intent = Intent(this@RegistroActivity, LoginActivity::class.java)
                    intent.putExtra("email1",email)
                    intent.putExtra("password1",password)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
        }

    }

    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}