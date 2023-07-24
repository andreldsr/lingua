package com.github.andreldsr.lingua.language

import io.micrometer.observation.annotation.Observed
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/admin/languages")
class LanguageAdminController(private val languageService: LanguageService) {

    @GetMapping("/{title}")
    @Operation(summary = "Find language by title", tags = ["Language"], security = [SecurityRequirement(name = "bearer-key")])
    fun findByTitle(@PathVariable title: String): Language? = languageService.findByTitle(title)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new language", tags = ["Language"], security = [SecurityRequirement(name = "bearer-key")])
    fun save(@RequestBody request: CreateLanguageRequest): Language = languageService.save(request)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete language by id", tags = ["Language"], security = [SecurityRequirement(name = "bearer-key")])
    fun deleteById(@PathVariable id: Long) = languageService.deleteById(id)
}
