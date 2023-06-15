package com.github.andreldsr.lingua.auth.role

import org.springframework.stereotype.Service

@Service
class RoleService(private val roleRepository: RoleRepository) {
    fun findAll() = roleRepository.findAll()

    fun findByName(name: String) = roleRepository.findByName(name)
}
