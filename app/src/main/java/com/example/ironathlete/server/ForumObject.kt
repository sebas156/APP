package com.example.ironathlete.server

import java.io.Serializable

data class ForumObject (
    var userName: String? = "",
    var likes: Int? = null,
    var comments: ArrayList<String>,
    var content: String? = "",
    var profilePicture: String?=""
): Serializable