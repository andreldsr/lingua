package com.github.andreldsr.lingua.language

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin/languages")
class LanguageAdminController(private val languageService: LanguageService) {

    @GetMapping
    @Operation(summary = "List all languages", tags = ["Language"], security = [SecurityRequirement(name = "bearer-key")])
    fun findAll(): List<Language> = languageService.findAll()

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
