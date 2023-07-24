package com.github.andreldsr.lingua.userstory

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

@Entity
data class UserStory(
    @Id
    val id: Long,
    val userId: UUID,
    val storyId: Long
)
