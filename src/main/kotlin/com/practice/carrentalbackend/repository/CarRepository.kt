package com.practice.carrentalbackend.repository

import com.practice.carrentalbackend.entity.Car
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CarRepository : JpaRepository<Car, Long> {

    @Query(value = "SELECT * FROM Car c WHERE c.car_id NOT IN ?1", nativeQuery = true)
    fun findByNotInRentedCarsId(rentedCarsId: List<Int>): List<Car>
}