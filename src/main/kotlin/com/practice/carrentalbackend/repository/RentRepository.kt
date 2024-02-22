package com.practice.carrentalbackend.repository

import com.practice.carrentalbackend.entity.Rent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface RentRepository: JpaRepository<Rent, Long> {

    @Query(value = "SELECT r.car_id FROM Rent r WHERE " +
            "(?1 BETWEEN r.rent_start AND r.rent_end)" +
            " OR (?2 BETWEEN r.rent_start AND r.rent_end)" +
            " OR (r.rent_start <= ?1 AND r.rent_end >= ?2)" +
            " OR (r.rent_start >= ?1 AND r.rent_end <= ?2)",
        nativeQuery = true)
    fun getRentedCarIdsWithinPeriod(rentStart: LocalDate, rentEnd: LocalDate): List<Int>
}