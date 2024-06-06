package com.example.health_planner.models

data class User(
    val user_id: Int,
    val email: String,
    val hashed_password: String,
    val name: String,
    val date_of_birth: String,
    val preferences: String // Assuming JSON stored as String
)