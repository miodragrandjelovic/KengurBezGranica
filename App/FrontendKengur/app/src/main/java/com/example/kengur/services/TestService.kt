package com.example.kengur.services

import com.example.kengur.dtos.request.RegisterRequest
import com.example.kengur.dtos.response.MessageResponse
import com.example.kengur.dtos.response.TaskResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TestService {

    @GET("api/Assignment/GenerateTest/{Class}")
    fun generateTest(@Path(value = "Class") Class: String): Call<ArrayList<TaskResponse>>
}