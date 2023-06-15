package com.github.andreldsr.lingua.auth.user

import com.github.andreldsr.lingua.auth.role.Role
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import org.hibernate.Hibernate
import java.util.UUID

@Entity
@Table(name = "\"user\"")
data class User(
    @Id
    @GeneratedValue(generator = "UUID")
    val id: UUID? = null,
    val name: String,
    val email: String,
    val password: String,
    @ManyToMany
    val roles: List<Role> = emptyList()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as User

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}
