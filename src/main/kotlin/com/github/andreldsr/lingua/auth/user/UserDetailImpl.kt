package com.github.andreldsr.lingua.auth.user

import com.github.andreldsr.lingua.auth.role.Role
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

data class UserDetailImpl(
    val id: UUID,
    val email: String,
    val name: String,
    val userPassword: String,
    val roles: List<Role>
) : UserDetails {
    override fun getAuthorities() = roles
    override fun getPassword() = userPassword
    override fun getUsername() = email
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}

fun User.toUserDetail() = UserDetailImpl(
    id = id!!,
    email = email,
    name = name,
    userPassword = password,
    roles = roles
)
