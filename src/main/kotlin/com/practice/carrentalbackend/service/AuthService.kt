package com.practice.carrentalbackend.service

import com.example.demo.entity.request.AuthenticationRequest
import com.practice.carrentalbackend.entity.User
import com.practice.carrentalbackend.entity.auth.AuthenticationResponse
import com.practice.carrentalbackend.repository.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    val authManager: AuthenticationManager,
    val userDetailsService: CustomUserDetailsService,
    val tokenService: TokenService,
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder
) {
    fun authentication(authRequest: AuthenticationRequest): AuthenticationResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password
            )
        )

        val user = userDetailsService.loadUserByUsername(authRequest.email)
        val accessToken = tokenService.generate(user)
        val userRole = userRepository.findRoleByEmail(user.username)
        return AuthenticationResponse(accessToken, user.username, userRole)
    }

    fun registerUser(user: User): String {
        val toSave = user.copy(password = passwordEncoder.encode(user.password))
        userRepository.save(toSave)
        return "User saved successfully";
    }

}
