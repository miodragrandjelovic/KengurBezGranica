package com.example.kengur.models

import com.google.gson.annotations.SerializedName

data class School(
    @SerializedName("id")
    var id:String,
    @SerializedName("name")
    var name:String,
    @SerializedName("city")
    var city:String,

)
