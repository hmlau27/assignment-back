package com.practice.carrentalbackend.entity

import jakarta.persistence.*

@Entity
data class Car(
    @Id
    @SequenceGenerator(name = "car_sequence", sequenceName = "car_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_sequence")
    val carId: Long?,
    val carModel: String,
    val carType: String,
    val carBrand: String,
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "car")
    val rents: MutableList<Rent>?
) {
    constructor(carModel: String, carType: String, carBrand: String): this(null, carModel, carType, carBrand, mutableListOf())
}