package com.github.andreldsr.lingua.word

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface WordRepository : JpaRepository<Word, Long> {
    fun existsByContentAndLanguageId(word: String, languageId: Long): Boolean
    fun findByLanguageId(languageId: Long, page: Pageable): Page<Word>
    fun findByLanguageIdAndContentContainingIgnoreCase(
        languageId: Long,
        content: String,
        pageable: Pageable
    ): Page<Word>
}
