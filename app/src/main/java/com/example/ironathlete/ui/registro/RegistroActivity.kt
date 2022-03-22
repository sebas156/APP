package com.example.ironathlete.ui.registro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.ironathlete.databinding.ActivityRegistroBinding
import com.example.ironathlete.ui.login.LoginActivity


class RegistroActivity : AppCompatActivity() {

    private lateinit var registroBinding: ActivityRegistroBinding
    private lateinit var registroViewModel: RegistroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registroBinding = ActivityRegistroBinding.inflate(layoutInflater)
        registroViewModel = ViewModelProvider(this).get(RegistroViewModel::class.java)
        setContentView(registroBinding.root)

        registroViewModel.msgDone.observe(this, { message ->
            onMsgDoneSuscribe(message)
        })

        registroViewModel.dataValidated.observe(this, { result ->
            onDataValidateSubscribe(result)
        })

        registroViewModel.registerSuccessDone.observe(this){ result ->
            onRegisterSuccessDoneSuscribe(result)
        }

        with(registroBinding) {
            RegistroButton.setOnClickListener {
                registroViewModel.validateFields(
                    UserEmailTextEdit2.text.toString(),
                    passwordTextEdit.text.toString(),
                    repasswordEditText.text.toString()
                )
            }
        }

    }

    private fun onRegisterSuccessDoneSuscribe(uid: String?) {
        registroViewModel.createUser(uid,registroBinding.UserEmailTextEdit2.text.toString())
        val intent = Intent(this@RegistroActivity, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)

    }

    private fun onDataValidateSubscribe(result: Boolean?) {

        with(registroBinding){
            Log.d("TAG", UserEmailTextEdit2.text.toString() + " " + passwordTextEdit.text.toString())
            registroViewModel.saveUser(UserEmailTextEdit2.text.toString(), passwordTextEdit.text.toString())
        }
    }

    private fun onMsgDoneSuscribe(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}