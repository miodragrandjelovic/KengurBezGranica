package com.example.kengur.dtos.response

import com.google.gson.annotations.SerializedName

data class MessageResponse(
    @SerializedName("message")
    var message: String
)
