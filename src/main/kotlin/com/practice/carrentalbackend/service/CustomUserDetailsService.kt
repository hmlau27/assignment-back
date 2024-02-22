package com.practice.carrentalbackend.service

import com.practice.carrentalbackend.entity.User
import com.practice.carrentalbackend.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

@Service
class CustomUserDetailsService(val userRepository: UserRepository): UserDetailsService  {
    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findByEmail(username)
            ?.mapToUserDetails()
            ?: throw UsernameNotFoundException("User not found!")


    private fun User.mapToUserDetails(): UserDetails =
        org.springframework.security.core.userdetails.User
            .builder()
            .username(this.email)
            .password(this.password)
            .roles(this.role.name)
            .build()
}