package com.example.ironathlete.ui.infoUser

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ironathlete.databinding.FragmentInfoUserBinding
import com.example.ironathlete.server.UserObject

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

        infoUserViewModel.userUpdatedDone.observe(viewLifecycleOwner){
            result ->
            onUserUpdatedDoneSuscribe()
        }


        infoUserBinding.timeButton.setOnClickListener {
            showTimePickerDialog()
        }

        infoUserBinding.saveButton.setOnClickListener {
            actualizarDatos()
        }
    }

    private fun onUserUpdatedDoneSuscribe() {
        Toast.makeText(requireContext(),"Informacion actualizada correctamente",Toast.LENGTH_SHORT).show()
    }

    private fun showTimePickerDialog() {
        val timePicker = TimePickerFragment{time -> onTimeSelected(time)}
        timePicker.show(parentFragmentManager, "time")
    }

    private fun onTimeSelected(time:String){
        infoUserBinding.timeButton.text=time
    }

    private fun actualizarDatos() {
        val verificado = actualizarDatosMemoriaTemporal()
        if(verificado) {
            currentUser.caloricRequirement=infoUserViewModel.calcularRequerimientoCalorico(currentUser.weight, currentUser.gender,currentUser.height,currentUser.age,currentUser.levelExercise)
            currentUser.caloricObjective = infoUserViewModel.calcularObjetivoClorico(currentUser.caloricRequirement!!,currentUser.objetive)
            currentUser.requiredProtein = infoUserViewModel.calcularProteinasRequeridas(currentUser.weight,currentUser.levelExercise)
            currentUser.requiredFats = infoUserViewModel.calcularGrasasRequeridas(currentUser.weight,currentUser.levelExercise)
            currentUser.requiredCarbs = infoUserViewModel.calcularCarbsRequeridos(currentUser.requiredProtein,currentUser.requiredFats,currentUser.caloricObjective)
            actualizarDatosBaseDatos()
        }
        else Toast.makeText(requireContext(),"Por favor rellene todos los campos de manera valida",Toast.LENGTH_SHORT).show()
    }

    private fun actualizarDatosBaseDatos() {
        infoUserViewModel.updateInfoUser(currentUser)
    }

    private fun actualizarDatosMemoriaTemporal() : Boolean {
        with(infoUserBinding){
            if(userNameTextEdit.text.toString() == "" || ageTextEdit.text.toString()=="" || heightTextEdit.text.toString() == "" || weigthEditText.text.toString()== "" || emailEditText.text.toString() == "" ) return false
            currentUser.fullName = userNameTextEdit.text.toString()
            currentUser.age=ageTextEdit.text.toString().toInt()
            currentUser.height=heightTextEdit.text.toString().toDouble()
            currentUser.weight=weigthEditText.text.toString().toDouble()
            currentUser.email = emailEditText.text.toString()

            if (!radioButtonMale.isChecked && !radioButtonFemale.isChecked) return false
            when(radioButtonMale.isChecked){
                true -> currentUser.gender="Hombre"
                false -> currentUser.gender="Mujer"
            }

            if(timeButton.text.toString()=="hh-mm-ss" || timeButton.text.toString()=="HH-MM-SS") return false
            currentUser.hourWorkOut=timeButton.text.toString()

            if (!radioButtonYes.isChecked && !radioButtonNO.isChecked) return false
            when(radioButtonYes.isChecked){
                true -> currentUser.goToGym=true
                false -> currentUser.goToGym=false
            }

            if (!bulkRadioButton.isChecked && !shreddedRadioButton.isChecked) return false
            when(bulkRadioButton.isChecked){
                true -> currentUser.objetive="bulk"
                false -> currentUser.objetive="shredded"
            }

            if (!radioButtonPoco.isChecked && !radioButtonLigero.isChecked && !radioButtonFuerte.isChecked && !radioButtonMuy.isChecked && !radioButtonModerado.isChecked) return false
            when {
                radioButtonPoco.isChecked -> currentUser.levelExercise = 1
                radioButtonLigero.isChecked -> currentUser.levelExercise = 2
                radioButtonModerado.isChecked -> currentUser.levelExercise = 3
                radioButtonFuerte.isChecked -> currentUser.levelExercise = 4
                else -> currentUser.levelExercise = 5
            }
        }
        return true
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
                "Hombre" -> radioButtonMale.isChecked = true
                "Mujer" -> radioButtonFemale.isChecked = true
                else -> {}
            }
            if(currentUser.hourWorkOut != ""){
                timeButton.setText(currentUser.hourWorkOut)
            }

            if(currentUser.goToGym == true){
                radioButtonYes.isChecked = true
            }else if(currentUser.goToGym == false){
                radioButtonNO.isChecked = true
            }

            if(currentUser.objetive == "bulk"){
                bulkRadioButton.isChecked = true
            }
            else if(currentUser.objetive == "shredded"){
                shreddedRadioButton.isChecked = true
            }

            when (currentUser.levelExercise) {
                1 -> radioButtonPoco.isChecked=true
                2 -> radioButtonLigero.isChecked=true
                3 -> radioButtonModerado.isChecked=true
                4 -> radioButtonFuerte.isChecked=true
                5 -> radioButtonMuy.isChecked = true
                else -> {}
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