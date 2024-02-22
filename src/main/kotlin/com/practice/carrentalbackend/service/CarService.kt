package com.practice.carrentalbackend.service

import com.practice.carrentalbackend.entity.Car
import java.time.LocalDate
import java.util.*

interface CarService {
    fun addCar(car: Car): Car
    fun getAll(): List<Car>
    fun getById(carId: Long): Optional<Car>
    fun deleteById(carId: Long): String
    fun getFreeCarsWithinPeriod(rentStart: LocalDate, rentEnd: LocalDate): List<Car>
}