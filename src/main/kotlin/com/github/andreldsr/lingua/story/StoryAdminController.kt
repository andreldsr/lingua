package com.github.andreldsr.lingua.story

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin/stories")
class StoryAdminController(private val storyService: StoryService) {
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
        summary = "Delete story by id",
        tags = ["Stories"],
        security = [SecurityRequirement(name = "bearer-key")]
    )
    fun delete(@PathVariable id: Long) = storyService.delete(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Create new story",
        tags = ["Stories"],
        security = [SecurityRequirement(name = "bearer-key")]
    )
    fun save(@RequestBody request: CreateStoryRequest) = storyService.save(request)

    @PatchMapping("/{id}/cover")
    @Operation(
        summary = "Update story cover",
        tags = ["Stories"],
        security = [SecurityRequirement(name = "bearer-key")]
    )
    fun updateCover(@PathVariable id: Long, @RequestBody request: UpdateCoverRequest) =
        storyService.updateCover(id, request)
}
