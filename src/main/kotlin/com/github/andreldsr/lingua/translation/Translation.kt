package com.github.andreldsr.lingua.translation

import com.github.andreldsr.lingua.language.Language
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
data class Translation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val content: String,
    @ManyToOne
    val language: Language
)
