package com.github.andreldsr.lingua.story

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/stories")
class StoryController(private val storyService: StoryService) {
    @GetMapping
    @Operation(summary = "List all stories", tags = ["Stories"], security = [SecurityRequirement(name = "bearer-key")])
    fun findAll() = storyService.findAll()

    @GetMapping("/{id}")
    @Operation(summary = "Find story by Id", tags = ["Stories"], security = [SecurityRequirement(name = "bearer-key")])
    fun find(@PathVariable id: Long) = storyService.find(id)

    @GetMapping("/{languageId}/level/{level}")
    @Operation(
        summary = "Find story by language id and level",
        tags = ["Stories"],
        security = [SecurityRequirement(name = "bearer-key")]
    )
    fun findByLanguageIdAndLevel(@PathVariable languageId: Long, @PathVariable level: String) =
        storyService.findByLanguageIdAndLevel(languageId, level)
}
