package com.github.andreldsr.lingua.question

import com.github.andreldsr.lingua.answer.Answer
import com.github.andreldsr.lingua.language.Language
import jakarta.persistence.*
import org.hibernate.Hibernate

@Entity
data class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val content: String = "",
    @OneToMany(mappedBy = "questionId", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val answers: List<Answer> = emptyList(),
    val storyId: Long? = null,
    @ManyToOne
    val language: Language? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Question

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}
