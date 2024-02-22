package com.practice.carrentalbackend.controller

import com.practice.carrentalbackend.entity.User
import com.practice.carrentalbackend.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping(path = ["/user"])
class UserController(val userService: UserService) {

    @GetMapping("/get/{userId}")
    fun getUserById(@PathVariable userId: Long): ResponseEntity<Any> {
        val user = userService.getById(userId);
        return if (user.isEmpty) ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exists!") else ResponseEntity.ok(user);
    }

}