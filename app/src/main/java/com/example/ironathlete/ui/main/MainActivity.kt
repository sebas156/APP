package com.example.ironathlete.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.ironathlete.R
import com.example.ironathlete.databinding.ActivityMainBinding
import com.example.ironathlete.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding
    var emailReceived: String?= ""
    var passwordReceived: String?=""

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "Have a nice day", Toast.LENGTH_LONG).show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val credentials = intent.extras
        if(credentials != null){
            emailReceived = credentials.getString("email")
            passwordReceived=credentials.getString("password")
        }

            mainBinding.DisplayEditText.text=emailReceived


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.sign_out ->goToLoginActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToLoginActivity(){
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("email1",emailReceived)
        intent.putExtra("password1",passwordReceived)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}