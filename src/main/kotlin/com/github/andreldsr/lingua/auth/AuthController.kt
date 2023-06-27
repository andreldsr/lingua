package com.github.andreldsr.lingua.auth

import com.github.andreldsr.lingua.auth.config.JWTUtils
import com.github.andreldsr.lingua.auth.user.RegisterRequest
import com.github.andreldsr.lingua.auth.user.UserService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userDetailsService: UserDetailsService,
    private val jwtUtils: JWTUtils,
    private val authenticationManager: AuthenticationManager,
    private val userService: UserService
) {

    @PostMapping
    @Operation(summary = "Authenticate user", tags = ["Auth"])
    fun authenticate(@RequestBody request: AuthRequest): ResponseEntity<String> {
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(request.email, request.password))
        val user = userDetailsService.loadUserByUsername(request.email)
        return generateResponse(user)
    }

    @PostMapping("/register")
    @Operation(summary = "Register new user", tags = ["Auth"])
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<String> {
        val newUser = userService.registerNewUser(request)
        val user = userDetailsService.loadUserByUsername(newUser.email)
        return generateResponse(user)
    }

    private fun generateResponse(user: UserDetails): ResponseEntity<String> {
        val token = jwtUtils.generateToken(user)
        val headers = HttpHeaders()
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer $token")
        return ResponseEntity
            .ok()
            .headers(headers)
            .body("Token generated successfully")
    }
}
