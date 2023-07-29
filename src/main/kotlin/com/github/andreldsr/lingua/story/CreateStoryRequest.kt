package com.github.andreldsr.lingua.story

import com.github.andreldsr.lingua.question.CreateQuestionRequest

data class CreateStoryRequest(
    val title: String,
    val content: String,
    val level: Int,
    val languageId: Long,
    val quiz: List<CreateQuestionRequest> = emptyList(),
    val cover: String? = null
)
