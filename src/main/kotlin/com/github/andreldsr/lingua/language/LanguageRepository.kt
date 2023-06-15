package com.github.andreldsr.lingua.language

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface LanguageRepository : JpaRepository<Language, Long> {
    fun existsByTitle(title: String): Boolean
    fun findByTitle(title: String): Optional<Language>
}
