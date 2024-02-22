package com.practice.carrentalbackend.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey

@Service
class TokenService {

    @Value("\${jwt.key}")
    private lateinit var secretKey: String

    @Value("\${jwt.access-token-expiration}")
    private var toExpiration: Int = 36000

    fun generate(userDetails: UserDetails): String =
        Jwts.
            builder().
            subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + toExpiration))
            .signWith(getKey())
            .compact();

    private fun getAllClaims(token: String): Claims {
        val parser = Jwts
            .parser()
            .verifyWith(getKey())
            .build()

        return parser
            .parseSignedClaims(token)
            .payload
    }

    fun getKey(): SecretKey =
        Keys.hmacShaKeyFor(secretKey.toByteArray());

    fun extractEmail(token: String): String? =
        getAllClaims(token).subject

    fun isExpired(token: String): Boolean =
        getAllClaims(token)
            .expiration
            .before(Date(System.currentTimeMillis()))

    fun isValid(token: String, userDetails: UserDetails): Boolean {
        val email = extractEmail(token)

        return email == userDetails.username && !isExpired(token)
    }
}