package com.example.ironathlete.server

import java.io.Serializable

data class IngredientObject (

        var foodId: String?="",
        var intake: Double? = null,
        var macro: String? = "",
        var amount: Double? =null,
        var name: String? = ""

):Serializable