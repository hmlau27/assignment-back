package com.practice.carrentalbackend.service

import com.practice.carrentalbackend.entity.Rent
import java.time.LocalDate

interface RentService {
    fun rentCar(rentStart: LocalDate, rentEnd: LocalDate, userId: Long, carId: Long): String
    fun getAll(): List<Rent>
}