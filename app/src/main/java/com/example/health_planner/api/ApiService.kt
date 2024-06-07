package com.example.health_planner.api

import com.example.health_planner.models.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("sign-in") // Replace with your actual login endpoint
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("sign-up") // Replace with your actual registration endpoint
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>

    @GET("reminder/{userId}")
    fun getReminders(@Path("userId") userId: Int): Call<List<Reminder>>

    @POST("reminder")
    fun addReminder(@Body request: Reminder): Call<ReminderResponse>

    @PUT("reminder/{reminderId}")
    fun updateReminder(@Path("reminderId") reminderId: Int, @Body request: Reminder): Call<ReminderResponse>

    @DELETE("reminder/{reminderId}")
    fun deleteReminder(@Path("reminderId") reminderId: Int): Call<ReminderResponse>
    // Add other endpoints as needed


    @GET("user/{userId}")
    fun getUser(@Path("userId") userId: Int): Call<User>
}
