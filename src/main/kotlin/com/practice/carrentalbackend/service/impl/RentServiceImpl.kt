package com.practice.carrentalbackend.service.impl

import com.practice.carrentalbackend.entity.Rent
import com.practice.carrentalbackend.repository.RentRepository
import com.practice.carrentalbackend.service.CarService
import com.practice.carrentalbackend.service.RentService
import com.practice.carrentalbackend.service.UserService
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.UUID

@Service
class RentServiceImpl(val rentRepository: RentRepository, val userService: UserService, val carService: CarService):
    RentService {

    override fun rentCar(rentStart: LocalDate, rentEnd: LocalDate, userId: Long, carId: Long): String {
        val user = userService.getById(userId)
        val car = carService.getById(carId)

        val rent = Rent(null, rentStart, rentEnd, user.get(), car.get(), UUID.randomUUID().toString());
        rentRepository.save(rent);

        return "Car $carId rented successfully."
    }

    override fun getAll(): List<Rent> {
        return rentRepository.findAll();
    }

}