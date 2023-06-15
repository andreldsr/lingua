package com.github.andreldsr.lingua.auth.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.Objects

@Component
class JWTAuthFilter(
    private val userDetailsService: UserDetailsService,
    private val jwtUtils: JWTUtils
) : OncePerRequestFilter() {
    val tokenStart = "Bearer "
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwtToken = getToken(request, filterChain, response)
        if (jwtToken.isBlank()) return
        val userDetails = getUserDetails(jwtToken, filterChain, request, response)

        if (userDetails == null) {
            filterChain.doFilter(request, response)
            return
        }
        val isTokenValid = jwtUtils.isTokenValid(jwtToken, userDetails)
        if (!isTokenValid) {
            filterChain.doFilter(request, response)
            return
        }
        handleToken(userDetails, request)
        filterChain.doFilter(request, response)
    }

    private fun handleToken(
        userDetails: UserDetails,
        request: HttpServletRequest
    ) {
        val authToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authToken
    }

    private fun getUserDetails(
        jwtToken: String,
        filterChain: FilterChain,
        request: HttpServletRequest,
        response: HttpServletResponse
    ): UserDetails? {
        val userEmail = jwtUtils.extractUsername(jwtToken)
        if (userEmail == null && SecurityContextHolder.getContext().authentication != null) {
            filterChain.doFilter(request, response)
            return null
        }
        return userDetailsService.loadUserByUsername(userEmail)
    }

    private fun getToken(
        request: HttpServletRequest,
        filterChain: FilterChain,
        response: HttpServletResponse
    ): String {
        val authHeader = request.getHeader(AUTHORIZATION)
        if (Objects.isNull(authHeader) || authHeader.isBlank() || !authHeader.startsWith(tokenStart)) {
            filterChain.doFilter(request, response)
            return ""
        }
        return authHeader.substring(tokenStart.length)
    }
}
