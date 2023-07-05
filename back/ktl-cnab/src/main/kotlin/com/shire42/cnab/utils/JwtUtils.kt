package com.shire42.cnab.utils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*

@Component
class JwtUtils(
    private val securityProperties: SecurityProperties
) {

    fun generateToken(username: String): String {
        val authClaims: MutableList<String> = mutableListOf()

        return Jwts.builder()
            .setSubject(username)
            .claim("auth", authClaims)
            .setIssuedAt(Date())
            .setExpiration(getExpirationDate())
            .signWith(Keys.hmacShaKeyFor(securityProperties.secret.toByteArray()), SignatureAlgorithm.HS512)
            .compact()
    }

    fun generateRefreshToken(username: String): String {
        val authClaims = makeClaims(username)

        return Jwts.builder()
            .setSubject(username)
            .claim("auth", authClaims)
            .setIssuedAt(Date())
            .setExpiration(getExpirationDate())
            .signWith(Keys.hmacShaKeyFor(securityProperties.secret.toByteArray()), SignatureAlgorithm.HS512)
            .compact()
    }


    fun getExpirationDate(): Date {
        return Date().add(Calendar.DAY_OF_MONTH, securityProperties.expirationTime)
    }


    fun Date.add(field: Int, amount: Int): Date {
        Calendar.getInstance().apply {
            time = this@add
            add(field, amount)
            return time
        }
    }

    private fun makeClaims(username: String): MutableMap<String, String> {
        val authClaims: MutableMap<String, String> = mutableMapOf()
        authClaims["time"] = Instant.now().toString()
        authClaims["username"] = username
        authClaims["date"] = getExpirationDate().toString()
        authClaims["expirationTime"] = securityProperties.expirationTime.toString()

        return authClaims
    }

}