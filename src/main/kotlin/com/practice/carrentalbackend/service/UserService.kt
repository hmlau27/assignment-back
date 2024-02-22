package com.practice.carrentalbackend.service

import com.practice.carrentalbackend.entity.User
import org.springframework.http.ResponseEntity
import java.util.Optional

interface UserService {
    fun getById(userId: Long): Optional<User>
    fun addUser(user: User): String
}