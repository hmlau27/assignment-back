package com.practice.carrentalbackend.service

import com.practice.carrentalbackend.entity.User
import org.springframework.http.ResponseEntity
import java.util.Optional

interface UserService {
    fun findByEmail(email: String): User?
}