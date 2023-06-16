package com.github.andreldsr.lingua.question

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository : JpaRepository<Question, Long> {
    @EntityGraph(attributePaths = ["answers"])
    fun findByStoryId(id: Long): List<Question>
}
