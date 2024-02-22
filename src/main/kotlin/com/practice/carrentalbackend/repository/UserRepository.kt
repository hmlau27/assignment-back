package com.practice.carrentalbackend.repository

import com.practice.carrentalbackend.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?

    @Query(value = "SELECT u.role FROM User u WHERE u.email = ?1", nativeQuery = true)
    fun findRoleByEmail(username: String): String?
}