package com.github.andreldsr.lingua.language

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface LanguageRepository : JpaRepository<Language, Long> {
    fun existsByCode(code: String): Boolean
    fun findByCode(code: String): Optional<Language>
}
