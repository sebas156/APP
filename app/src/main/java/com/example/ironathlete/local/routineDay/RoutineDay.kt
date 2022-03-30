package com.example.ironathlete.local.routineDay

import java.io.Serializable

data class RoutineDay(
    var id: String? = null,
    var day: Int? = null,
    var exercisesList: Map<String, Boolean>? = null,
    var image: String? = null): Serializable
