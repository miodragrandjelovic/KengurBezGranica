package com.example.kengur.dtos.request

import com.google.gson.annotations.SerializedName

data class ResultRequest(

    @SerializedName("email")
    var email: String,
    @SerializedName("score")
    var score: Float,
    @SerializedName("class")
    var userClass: String

)
