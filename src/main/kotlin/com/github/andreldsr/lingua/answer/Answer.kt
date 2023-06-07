package com.github.andreldsr.lingua.answer

import com.github.andreldsr.lingua.question.Question
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
data class Answer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val content: String,
    @Column(name = "is_correct")
    val isCorrect: Boolean,
    @ManyToOne
    @JoinColumn(name = "question_id")
    val question: Question? = null
)
