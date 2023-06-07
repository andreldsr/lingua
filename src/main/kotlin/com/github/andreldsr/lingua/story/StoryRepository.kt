package com.github.andreldsr.lingua.story

import org.springframework.data.jpa.repository.JpaRepository

interface StoryRepository : JpaRepository<Story, Long>
