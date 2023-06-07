package com.github.andreldsr.lingua.word

import com.github.andreldsr.lingua.translation.Translation
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany

@Entity
data class Word(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val content: String,
    @ManyToMany
    val translations: List<Translation>
)
