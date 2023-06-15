package com.github.andreldsr.lingua.auth.user

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface UserRepository : JpaRepository<User, UUID> {
    @EntityGraph(attributePaths = ["roles"])
    fun findByEmail(email: String): Optional<User>
    fun existsByEmail(email: String): Boolean
}
