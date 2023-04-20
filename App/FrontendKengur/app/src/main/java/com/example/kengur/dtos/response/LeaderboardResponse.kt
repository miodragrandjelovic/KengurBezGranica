package com.example.kengur.dtos.response

import com.example.kengur.dtos.DTO.UserDTO
import com.google.gson.annotations.SerializedName

data class LeaderboardResponse(

   // @SerializedName("userImage")
  //  var userImage:String,
    @SerializedName("id")
    var id:String,
    @SerializedName("user")
    var userDTO:UserDTO,
    @SerializedName("points")
    var points:Float,
    @SerializedName("class")
    var testClass:String
)
