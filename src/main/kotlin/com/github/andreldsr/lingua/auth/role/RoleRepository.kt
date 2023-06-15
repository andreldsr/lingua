package com.github.andreldsr.lingua.auth.role

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface RoleRepository : JpaRepository<Role, UUID> {
    fun findByName(name: String): Role
}
