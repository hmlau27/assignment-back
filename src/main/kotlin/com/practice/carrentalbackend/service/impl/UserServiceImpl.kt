package com.practice.carrentalbackend.service.impl

import com.practice.carrentalbackend.repository.UserRepository
import com.practice.carrentalbackend.service.UserService
import com.practice.carrentalbackend.entity.User
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(val userRepository: UserRepository) : UserService {
    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

}