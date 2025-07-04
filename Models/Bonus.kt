package com.buyansell.models

data class Bonus(
    val id: String,
    val userId: String,
    val points: Int,
    val description: String?,
    val dateEarned: Long
)
