package com.github.andreldsr.lingua.story

import com.github.andreldsr.lingua.language.Language
import com.github.andreldsr.lingua.question.Question
import com.github.andreldsr.lingua.word.Word
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne

@Entity
data class Story(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val title: String = "",
    val content: String = "",
    val level: String = "",
    @ManyToOne
    val language: Language? = null,
    @ManyToMany
    val quiz: List<Question> = emptyList(),
    @ManyToMany
    val vocabulary: List<Word> = emptyList(),
    val cover: String = ""
)
