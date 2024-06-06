package com.example.health_planner.models

data class HealthJournalEntry(
        val entry_id: Int,
        val user_id: Int,
        val entry_date: String,
        val symptoms: String,
        val condition_description: String,
        val other_notes: String
)