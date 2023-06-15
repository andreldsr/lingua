package com.github.andreldsr.lingua.auth.user

data class RegisterRequest(
    val email: String,
    val name: String,
    val password: String
)
