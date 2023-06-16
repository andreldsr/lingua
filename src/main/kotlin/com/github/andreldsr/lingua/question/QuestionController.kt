package com.github.andreldsr.lingua.question

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/questions")
class QuestionController(private val questionService: QuestionService) {
    @GetMapping("/story/{id}")
    @Operation(
        summary = "Find questions by story id",
        tags = ["Question"],
        security = [SecurityRequirement(name = "bearer-key")]
    )
    fun findByStoryId(@PathVariable id: Long) = questionService.findByStoryId(id)
}
