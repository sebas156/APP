package com.example.ironathlete.server

data class ComunityObject (
    var id: String?="",
    var userName: String?= "",
    var content: String? = "",
    var image: String?="",
    var numberLikes: Int?  = null,
    var comments: ArrayList<String>,
    var ageUser: Int? = null,
    var date: String= "",
    var objetive: String? =""
)