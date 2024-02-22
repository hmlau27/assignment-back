package com.practice.carrentalbackend.controller

import com.practice.carrentalbackend.entity.Car
import com.practice.carrentalbackend.service.CarService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@CrossOrigin
@RestController
@RequestMapping(path = ["/car"])
class CarController(val carService: CarService) {

    @PostMapping("/add")
    fun addCar(@RequestBody car: Car): ResponseEntity<Car> {
        return ResponseEntity.ok(carService.addCar(car));
    }

    @GetMapping("/getAll")
    fun getAllCars(): ResponseEntity<List<Car>> {
        return ResponseEntity.ok(carService.getAll());
    }

    @GetMapping("/get/{carId}")
    fun getCarById(@PathVariable carId: Long): ResponseEntity<Any> {
        val car = carService.getById(carId);
        return if (car.isEmpty) ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car does not exists!") else ResponseEntity.ok(car);
    }

    @GetMapping("/get")
    fun getCarsAvailableWithinPeriod(@RequestParam("rentStart") rentStart: LocalDate, @RequestParam("rentEnd") rentEnd: LocalDate): ResponseEntity<List<Car>> {
        return ResponseEntity.ok(carService.getFreeCarsWithinPeriod(rentStart, rentEnd))
    }

    @DeleteMapping("/delete/{carId}")
    fun deleteCar(@PathVariable carId: Long): ResponseEntity<Any> {
        val result = carService.deleteById(carId).lowercase()
        return if (result.contains("deleted")) ResponseEntity.ok(result) else ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

}