package com.example.health_planner.models

data class Reminder(
    val reminder_id: Int,
    val user_id: Int,
    val medication_name: String,
    val dosage: String,
    val time_to_take: String,
    val frequency: String,
    val start_date: String,
    val end_date: String,
    val notes: String
)