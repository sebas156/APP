package com.example.ironathlete.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ironathlete.R
import com.example.ironathlete.databinding.ActivityMainBinding
import com.example.ironathlete.server.UserObject
import com.example.ironathlete.ui.login.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var currentUser: UserObject

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "Have a nice day", Toast.LENGTH_LONG).show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContentView(mainBinding.root)

        Log.d("position","Estoy en el on create del main")

        mainViewModel.loadedUserIdDone.observe(this){
                result ->
            onUserLoadedIdDoneSuscribe(result)
        }

        mainViewModel.userLoadedDone.observe(this){
            onUserLoadedDoneSuscribe()
        }

        val navView: BottomNavigationView = mainBinding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_bottom)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.muscleFragment, R.id.dietsFragment, R.id.communityFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        getUserId()
    }

    private fun onUserLoadedDoneSuscribe() {
        currentUser= mainViewModel.setUserLoaded()!!
    }

    private fun onUserLoadedIdDoneSuscribe(result: String?) {
        result?.let { mainViewModel.getUser(it) }
    }

    private fun getUserId() {
        mainViewModel.getUserId()
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
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    public fun getUserObject(): UserObject{
        return currentUser
    }
    public fun setUserObject(user: UserObject){
        currentUser = user
    }

    public fun getCaloriesObjective(): Double? {
        return currentUser.caloricObjective
    }

    public fun getCarbsRequirenment(): Double? {
        return currentUser.requiredCarbs
    }
    public fun getProteinRequirenment(): Double? {
        return currentUser.requiredProtein
    }
    public fun getFatsRequirenment(): Double? {
        return currentUser.requiredFats
    }


}