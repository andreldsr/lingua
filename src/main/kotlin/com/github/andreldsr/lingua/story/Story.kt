package com.github.andreldsr.lingua.story

import com.github.andreldsr.lingua.language.Language
import com.github.andreldsr.lingua.question.Question
import jakarta.persistence.*
import org.hibernate.Hibernate

@Entity
data class Story(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val title: String = "",
    val content: String = "",
    val level: Int = -1,
    @ManyToOne
    val language: Language? = null,
    @OneToMany(mappedBy = "storyId", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val quiz: List<Question> = emptyList(),
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
