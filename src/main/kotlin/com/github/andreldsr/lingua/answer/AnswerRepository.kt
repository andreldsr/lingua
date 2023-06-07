package com.github.andreldsr.lingua.answer

import org.springframework.data.jpa.repository.JpaRepository

interface AnswerRepository : JpaRepository<Answer, Long> {
    fun findByQuestionId(id: Long): List<Answer>
}
