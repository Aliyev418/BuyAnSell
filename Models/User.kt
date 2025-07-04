package com.buyansell.models

data class User(
    val id: String,
    val username: String,
    val role: UserRole
)

enum class UserRole {
    ADMIN,
    USER
}
