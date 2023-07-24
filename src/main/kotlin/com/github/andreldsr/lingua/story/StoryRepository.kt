package com.github.andreldsr.lingua.story

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import java.awt.print.Pageable
import java.util.Optional

interface StoryRepository : JpaRepository<Story, Long> {
    fun findByLanguageId(languageId: Long): List<StoryListDto>

    @EntityGraph(attributePaths = ["language", "quiz", "quiz.answers"])
    override fun findById(id: Long): Optional<Story>
    fun findBy(): List<StoryListDto>

    fun findTop1ByLanguageIdAndLevel(languageId: Long, level: String): Story?
}
