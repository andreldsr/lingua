package com.github.andreldsr.lingua.story

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/stories")
class StoryController(private val storyService: StoryService) {
    @GetMapping
    fun findAll() = storyService.findAll()

    @GetMapping("/{id}")
    fun find(@PathVariable id: Long) = storyService.find(id)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = storyService.delete(id)

    @PostMapping
    fun save(@RequestBody story: Story) = storyService.save(story)
}
