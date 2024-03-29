package com.github.andreldsr.lingua.language

import io.micrometer.observation.annotation.Observed
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/languages")
class LanguageController(private val languageService: LanguageService) {
    @Observed(name = "language.findAll")
    @GetMapping
    @Operation(summary = "List all languages", tags = ["Language"], security = [SecurityRequirement(name = "bearer-key")])
    fun findAll(): List<Language> = languageService.findAll()

    @GetMapping("/{code}")
    @Operation(summary = "Find language by code", tags = ["Language"], security = [SecurityRequirement(name = "bearer-key")])
    fun findByCode(@PathVariable code: String): Language? = languageService.findByCode(code)

}
