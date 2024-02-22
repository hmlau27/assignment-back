package com.practice.carrentalbackend.controller

import com.example.demo.entity.request.AuthenticationRequest
import com.practice.carrentalbackend.entity.User
import com.practice.carrentalbackend.entity.auth.AuthenticationResponse
import com.practice.carrentalbackend.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/auth")
class AuthController (
    val authenticationService: AuthService
) {

    @PostMapping("/sign-up")
    fun addUser(@RequestBody user: User): ResponseEntity<String> =
        ResponseEntity.ok(authenticationService.registerUser(user))


    @PostMapping("sign-in")
    fun authenticate(@RequestBody authRequest: AuthenticationRequest) : ResponseEntity<AuthenticationResponse> =
        ResponseEntity.ok(authenticationService.authentication(authRequest))
}