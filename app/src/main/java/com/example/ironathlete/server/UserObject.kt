package com.example.ironathlete.server

import java.io.Serializable

data class UserObject (

     var uid: String? ="",
     var email: String?="",
     var fullName: String? = "",
     var weight: Double? = null,
     var height: Double? = null,
     var age: Int? = null,
     var gender :String? = "",
     var hourWorkOut: String? ="",
     var goToGym: Boolean? = null,
     var objetive: String? = "",
     var caloricRequirement: Double?= null,
     var caloricObjective: Double? = null,
     var requiredProtein: Double? = null,
     var requiredCarbs: Double? = null,
     var requiredFats: Double? = null,
     var levelExercise: Int? = null
):Serializable