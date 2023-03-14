package com.example.kengur.utility

import android.content.Context
import com.example.kengur.services.UserService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ApiClient {

    private lateinit var userService: UserService

    private fun okHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
    }

    fun getUserService(context: Context): UserService {

        if(!::userService.isInitialized){

            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient(context))
                .build()

            userService = retrofit.create(UserService::class.java)

        }
        return  userService
    }
}