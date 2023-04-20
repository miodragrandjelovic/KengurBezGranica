package com.example.kengur.dtos.DTO

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("email")
    var email:String,
    @SerializedName("firstName")
    var firstName:String,
    @SerializedName("lastName")
    var lastName:String
)
