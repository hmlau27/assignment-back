package com.practice.carrentalbackend.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class Rent (
    @Id
    @SequenceGenerator(name = "rent_sequence", sequenceName = "rent_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rent_sequence")
    val rentId: Long?,
    val rentStart: LocalDate,
    val rentEnd: LocalDate,
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id", referencedColumnName = "userId", foreignKey = ForeignKey(name = "user_fk"))
    val user: User,
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "car_id", referencedColumnName = "carId", foreignKey = ForeignKey(name = "car_fk"))
    val car: Car,
    val rentCode: String
)