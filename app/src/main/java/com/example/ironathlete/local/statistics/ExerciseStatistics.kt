package com.example.ironathlete.local.statistics

import java.io.Serializable

data class ExerciseStatistics(
    var id: String? = null,
    var excercise_id: String? = null,
    var excercise_name: String? = null,
    var day: Int? = null,
    var weight: Double? = null,
    var repetitions: Int? = null
):Serializable
