package com.github.andreldsr.lingua.story

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface StoryRepository : JpaRepository<Story, Long> {
    fun findByLanguageId(languageId: Long): List<StoryListDto>

    @EntityGraph(attributePaths = ["language", "quiz", "vocabulary", "quiz.answers"])
    override fun findById(id: Long): Optional<Story>
    fun findBy(): List<StoryListDto>
}
