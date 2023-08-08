package com.example.kengur.dtos.request

import com.google.gson.annotations.SerializedName

data class StatisticRequest(
    @SerializedName("id")
    var id:String,
    @SerializedName("isCorrect")
    var isCorrect:Boolean
)
