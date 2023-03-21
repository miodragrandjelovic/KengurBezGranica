package com.example.kengur.services

import com.example.kengur.dtos.request.LoginRequest
import com.example.kengur.dtos.request.RegisterRequest
import com.example.kengur.dtos.response.LoginResponse
import com.example.kengur.dtos.response.MessageResponse
import com.example.kengur.models.School
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    @POST("api/Auth/login")
    fun login(@Body requestBody: LoginRequest): Call<LoginResponse>

    @POST("api/Auth/register")
    fun register(@Body requestBody: RegisterRequest) : Call<MessageResponse>

    @GET("api/School/SearchSchools/{name}")
    fun searchSchools(@Path(value = "name") name: String): Call<ArrayList<School>>

}