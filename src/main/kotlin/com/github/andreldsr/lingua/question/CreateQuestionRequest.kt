package com.github.andreldsr.lingua.question

data class CreateQuestionRequest(
    val content: String,
    val answers: List<CreateAnswerRequest>
)

data class CreateAnswerRequest(
    val content: String,
    val isCorrect: Boolean
)
