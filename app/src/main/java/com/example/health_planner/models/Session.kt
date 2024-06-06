package com.example.health_planner.models

data class Session(
        val session_id: Int,
        val user_id: Int,
        val login_time: String,
        val logout_time: String,
        val device_info: String,
        val session_token: String
)