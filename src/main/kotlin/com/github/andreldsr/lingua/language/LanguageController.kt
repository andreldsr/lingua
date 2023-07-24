package com.github.andreldsr.lingua.language

import io.micrometer.observation.annotation.Observed
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/languages")
class LanguageController(private val languageService: LanguageService) {

    @Observed(name = "language.findAll")
    @GetMapping
    @Operation(summary = "List all languages", tags = ["Language"], security = [SecurityRequirement(name = "bearer-key")])
    fun findAll(): List<Language> = languageService.findAll()
}
