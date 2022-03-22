package com.example.ironathlete.ui.infoUser

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ironathlete.R
import com.example.ironathlete.databinding.FragmentInfoUserBinding
import com.example.ironathlete.local.repository.UserRepository
import com.example.ironathlete.server.ServerRepositories.ServerUserRepository
import com.example.ironathlete.server.UserObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class InfoUserFragment : Fragment() {

    private lateinit var infoUserViewModel: InfoUserViewModel
    private lateinit var infoUserBinding: FragmentInfoUserBinding
    lateinit var currentUser : UserObject

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        infoUserBinding= FragmentInfoUserBinding.inflate(inflater,container,false)
        infoUserViewModel = ViewModelProvider(this)[InfoUserViewModel::class.java]
        return infoUserBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        infoUserViewModel.userLoadedDone.observe(viewLifecycleOwner){
            result ->
            onUserLoadedDoneSuscribe(result)
        }

        infoUserViewModel.loadedUserIdDone.observe(viewLifecycleOwner){
            result ->
            onUserLoadedIdDoneSuscribe(result)
        }
        getUserId()
    }

    private fun onUserLoadedIdDoneSuscribe(result: String?) {
        result?.let { infoUserViewModel.getUser(it) }
    }

    private fun pintarDatos() {
        with(infoUserBinding){
            userNameTextEdit.setText(currentUser.fullName)
            if(currentUser.age != null) ageTextEdit.setText(currentUser.age.toString())
            if(currentUser.height != null) heightTextEdit.setText(currentUser.height.toString())
            if(currentUser.weight != null) weigthEditText.setText(currentUser.weight.toString())
            emailEditText.setText(currentUser.email)
            when(currentUser.gender){
                "Homre" -> radioButtonMale.isChecked
                "Mujer" -> radioButtonFemale.isChecked
                else -> {}
            }
            if(currentUser.hourWorkOut != ""){
                timeButton.setText(currentUser.hourWorkOut)
            }

            if(currentUser.goToGym == true){
                radioButtonYes.isChecked
            }else{
                radioButtonNO.isChecked
            }

            if(currentUser.objetive == "bulk"){
                bulkRadioButton.isChecked
            }
            else{
                shreddedRadioButton.isChecked
            }
        }
    }

    private fun onUserLoadedDoneSuscribe(result: Boolean?) {
        currentUser= infoUserViewModel.setUserLoaded()!!
        pintarDatos()
    }

    private fun getUserId(){
        infoUserViewModel.getUserId()
    }


}