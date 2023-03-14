package com.example.kengur.dtos.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest (

    @SerializedName("firstName")
    var firstName: String,
    @SerializedName("lastName")
    var lastName: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("school")
    var school: String,
    @SerializedName("class")
    var userClass: Short,
    @SerializedName("password")
    var password: String

)