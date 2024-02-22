package com.practice.carrentalbackend.entity.auth

data class AuthenticationResponse (
    val accessToken: String,
    val email: String,
    val role: String
)