package com.example.kengur.dtos.response

import com.example.kengur.models.School
import com.example.kengur.utility.SessionManager
import com.google.gson.annotations.SerializedName

data class UserResponse(

    @SerializedName("firstName")
    var firstName: String,

    @SerializedName("lastName")
    var lastName: String,

    @SerializedName("school")
    var school: School,

    @SerializedName("class")
    var userClass: Short,

    @SerializedName("testNumber")
    var testNumber: Int,
    @SerializedName("sumPoints")
    var sumPoints: Float


)
