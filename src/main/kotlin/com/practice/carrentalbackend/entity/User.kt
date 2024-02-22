package com.practice.carrentalbackend.entity

import com.assignment.carrental.entity.Role
import jakarta.persistence.*

@Entity
data class User (
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    val userId: Long?,
    val firstName: String,
    val lastName: String,
    @Column(nullable = false, unique = true)
    val email: String,
    val password: String,
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "user")
    val rents: MutableList<Rent>?,
    @Enumerated(value = EnumType.STRING)
    val role: Role = Role.USER
)