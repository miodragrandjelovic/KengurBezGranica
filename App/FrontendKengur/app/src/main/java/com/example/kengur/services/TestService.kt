package com.example.kengur.services

import com.example.kengur.dtos.request.StatisticRequest
import com.example.kengur.dtos.response.MessageResponse
import com.example.kengur.dtos.response.TaskResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface TestService {

    @GET("api/Assignment/GenerateTest/{Class}")
    fun generateTest(@Path(value = "Class") Class: String): Call<ArrayList<TaskResponse>>


    @GET("api/Assignment/GetTasksFiltered/{class}/{level}")
    fun getTasksFiltered(
        @Path("class") classRange: String,
        @Path("level") level: Int
    ):  Call<ArrayList<TaskResponse>>

    @PUT("api/Assignment/SendStatistic")
    fun sendStatistic(@Body requestBody: ArrayList<StatisticRequest>) : Call<MessageResponse>

}