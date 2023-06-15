package com.github.andreldsr.lingua.auth.user

import com.github.andreldsr.lingua.auth.role.RoleRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder
) : UserDetailsService {
    fun findAll() = userRepository.findAll()

    fun findByEmail(email: String) = userRepository.findByEmail(email)

    fun findById(id: UUID) = userRepository.findById(id)

    fun create(user: User) = userRepository.save(user)

    fun update(id: UUID, user: User) = userRepository.findById(id)
        .map { userRepository.save(user) }
        .orElseThrow { RuntimeException("User not found") }

    fun delete(id: UUID) = userRepository.deleteById(id)
    fun registerNewUser(request: RegisterRequest): User {
        if (userRepository.existsByEmail(request.email)) throw UserAlreadyExistsException("User already exists")
        val role = roleRepository.findByName("ROLE_USER")
        val user = User(
            name = request.name,
            email = request.email,
            password = passwordEncoder.encode(request.password),
            roles = mutableListOf(role)
        )
        return userRepository.save(user)
    }

    override fun loadUserByUsername(username: String?) =
        userRepository.findByEmail(username ?: "").map { it.toUserDetail() }
            .orElseThrow { UserNotFoundException("User $username not found") }
}
