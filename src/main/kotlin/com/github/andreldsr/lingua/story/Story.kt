package com.github.andreldsr.lingua.story

import com.github.andreldsr.lingua.language.Language
import com.github.andreldsr.lingua.question.Question
import com.github.andreldsr.lingua.word.Word
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import org.hibernate.Hibernate

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
    @OneToMany(mappedBy = "storyId", fetch = FetchType.LAZY)
    val quiz: List<Question> = emptyList(),
    @ManyToMany(fetch = FetchType.LAZY)
    val vocabulary: List<Word> = emptyList(),
    val cover: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Story

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}
