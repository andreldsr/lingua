package com.github.andreldsr.lingua.question

import com.github.andreldsr.lingua.answer.Answer
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
data class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val content: String = "",
    @OneToMany(mappedBy = "question")
    val answers: List<Answer> = emptyList()
)
