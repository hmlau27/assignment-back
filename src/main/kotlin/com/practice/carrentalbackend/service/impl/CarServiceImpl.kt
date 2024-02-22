package com.practice.carrentalbackend.service.impl

import com.practice.carrentalbackend.repository.CarRepository
import com.practice.carrentalbackend.repository.RentRepository
import com.practice.carrentalbackend.service.CarService
import com.practice.carrentalbackend.entity.Car
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class CarServiceImpl(val carRepository: CarRepository, val rentRepository: RentRepository) : CarService {
    override fun addCar(car: Car): Car {
        return carRepository.save(car);
    }

    override fun getAll(): List<Car> {
        return carRepository.findAll();
    }

    override fun getById(carId: Long): Optional<Car> {
        return carRepository.findById(carId);
    }

    override fun deleteById(carId: Long): String {
        val car = getById(carId)
        if (car.isPresent) {
            carRepository.delete(car.get())
            return "Car deleted."
        }
        return "Car not found."
    }

    override fun getFreeCarsWithinPeriod(rentStart: LocalDate, rentEnd: LocalDate): List<Car> {
        val rentedCarsIds = rentRepository.getRentedCarIdsWithinPeriod(rentStart, rentEnd)
        return carRepository.findByNotInRentedCarsId(rentedCarsIds.ifEmpty { listOf(-99) })
    }
}