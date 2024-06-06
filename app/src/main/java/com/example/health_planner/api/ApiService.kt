package com.example.health_planner.api

import com.example.health_planner.models.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("login") // Replace with your actual login endpoint
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("register") // Replace with your actual registration endpoint
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>

    @GET("users/{id}")
    fun getUser(@Path("id") userId: Int): Call<User>

    @POST("users")
    fun createUser(@Body user: User): Call<User>

    @GET("reminders")
    fun getReminders(@Query("user_id") userId: Int): Call<List<Reminder>>

    @POST("reminders")
    fun createReminder(@Body reminder: Reminder): Call<Reminder>

    // Add other endpoints as needed
}
