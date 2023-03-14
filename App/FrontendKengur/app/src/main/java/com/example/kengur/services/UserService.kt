package com.example.kengur.services

import com.example.kengur.dtos.request.LoginRequest
import com.example.kengur.dtos.request.RegisterRequest
import com.example.kengur.dtos.response.LoginResponse
import com.example.kengur.dtos.response.MessageResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("api/Auth/login")
    fun login(@Body requestBody: LoginRequest): Call<LoginResponse>

    @POST("api/Auth/register")
    fun register(@Body requestBody: RegisterRequest) : Call<MessageResponse>

}