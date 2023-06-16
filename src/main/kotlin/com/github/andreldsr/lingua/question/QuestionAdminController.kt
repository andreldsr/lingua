package com.github.andreldsr.lingua.question

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin/questions")
class QuestionAdminController(private val questionService: QuestionService) {
    @PostMapping("/story/{id}")
    @Operation(
        summary = "Create new question for story",
        tags = ["Question"],
        security = [SecurityRequirement(name = "bearer-key")]
    )
    fun create(@PathVariable id: Long, @RequestBody request: CreateQuestionRequest) =
        questionService.create(id, request)
}