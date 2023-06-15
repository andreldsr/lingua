package com.github.andreldsr.lingua.auth.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm.HS256
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Date

@Component
class JWTUtils(
    @Value("\${jwt.secret}") private val jwtKey: String
) {
    fun extractUsername(token: String): String {
        return extractClaim(token, Claims::getSubject)
    }

    fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }

    private fun <T : Any> extractClaim(token: String, claimsResolver: (c: Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver.invoke(claims)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).body
    }

    fun hasClaim(token: String, claimName: String): Boolean {
        val claims: Claims = extractAllClaims(token)
        return claims[claimName] != null
    }

    fun generateToken(userDetails: UserDetails): String {
        val claims: Map<String, Any> = mutableMapOf()
        return createToken(claims, userDetails)
    }

    fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)))
    }

    fun generateToken(userDetails: UserDetails, claims: Map<String, Any>): String {
        return createToken(claims, userDetails)
    }

    private fun createToken(claims: Map<String, Any>, userDetails: UserDetails): String {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userDetails.username)
            .claim("authorities", userDetails.authorities)
            .setIssuedAt(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)))
            .setExpiration(Date.from(LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.UTC)))
            .signWith(getKey(), HS256)
            .compact()
    }

    private fun getKey() = Keys.hmacShaKeyFor(
        jwtKey.toByteArray(Charsets.UTF_8)
    )

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return (username == userDetails.username && !isTokenExpired(token))
    }
}
