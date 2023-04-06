package com.example.kengur.dtos.response

import com.google.gson.annotations.SerializedName

data class LeaderboardResponse(
    @SerializedName("rank")
    var rank:Int,
    @SerializedName("userImage")
    var userImage:String,
    @SerializedName("fullName")
    var fullName:String,
    @SerializedName("score")
    var score:Float
)
