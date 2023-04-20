package com.example.kengur.dtos.request

import com.google.gson.annotations.SerializedName

data class ResultRequest(

    @SerializedName("email")
    var email: String,
    @SerializedName("points")
    var score: Double,
    @SerializedName("class")
    var userClass: String

)
