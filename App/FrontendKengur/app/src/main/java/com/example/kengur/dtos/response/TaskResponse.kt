package com.example.kengur.dtos.response

import com.google.gson.annotations.SerializedName

data class TaskResponse(
    @SerializedName("taskText")
    var taskText: String,
    @SerializedName("taskPicture")
    var taskPicture: String,
    @SerializedName("answersText")
    var answersText: ArrayList<String>,
    @SerializedName("answersPictures")
    var answersPictures: ArrayList<String>,
    @SerializedName("correctAnswerIndex")
    var correctAnswerIndex: Int,
    @SerializedName("level")
    var level: Int
)
