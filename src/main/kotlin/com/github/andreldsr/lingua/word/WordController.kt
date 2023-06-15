package com.github.andreldsr.lingua.word

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin/words")
class WordController(private val wordService: WordService) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Save a word", tags = ["Word"], security = [SecurityRequirement(name = "bearer-key")])
    fun save(@RequestBody request: CreateWordRequest) = wordService.save(request)

    @PostMapping("/{id}/translation")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Add translation to a word",
        tags = ["Word"],
        security = [SecurityRequirement(name = "bearer-key")]
    )
    fun addTranslation(@RequestBody request: TranslationRequest, @PathVariable id: Long) =
        wordService.addTranslation(id, request)

    @GetMapping("/language/{languageId}")
    @Operation(
        summary = "Find words from a language",
        tags = ["Word"],
        security = [SecurityRequirement(name = "bearer-key")]
    )
    fun findByLanguage(
        @PathVariable languageId: Long,
        @PageableDefault(page = 0, size = 10, sort = ["content"]) pageable: Pageable,
        @RequestParam(required = false) content: String? = null
    ) = wordService.findByLanguage(languageId, content, pageable)
}
