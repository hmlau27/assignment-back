package com.practice.carrentalbackend.controller

import com.practice.carrentalbackend.entity.Rent
import com.practice.carrentalbackend.service.RentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@CrossOrigin
@RequestMapping(path = ["/rent"])
@RestController
class RentController(val rentService: RentService) {

    @GetMapping("/get")
    fun getAllRents() : ResponseEntity<List<Rent>> {
        return ResponseEntity.ok(rentService.getAll())
    }

    @PostMapping("/user/{userId}/car/{carId}")
    fun bookCar(@RequestParam("rentStart") rentStart: LocalDate, @RequestParam("rentEnd") rentEnd: LocalDate, @PathVariable userId: Long, @PathVariable carId: Long) : ResponseEntity<String> {
        return ResponseEntity.ok(rentService.rentCar(rentStart, rentEnd, userId, carId))
    }
}